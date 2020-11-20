package com.siwuxie095.network.chapter7th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-20 21:17:14
 */
public class Main {

    /**
     * 实现细节
     *
     * 这里将更加详细地探讨 Netty 的线程模型和任务调度实现的主要内容。同时也将会提到需要注意的局限性，以及正在
     * 不断发展中的领域。
     *
     *
     *
     * 线程管理
     *
     * Netty 线程模型的卓越性能取决于对于当前执行的 Thread 的身份的确定，也就是说，确定它是否是分配给当前
     * Channel 以及它的 EventLoop 的那一个线程（通过调用 EventLoop 的 inEventLoop(Thread) 方法实现）。
     *
     * EventLoop 将负责处理一个 Channel 的整个生命周期内的所有事件。
     *
     * 如果（当前）调用线程正是支撑 EventLoop 的线程，那么所提交的代码块将会被（直接）执行。否则，EventLoop
     * 将调度该任务以便稍后执行，并将它放入到内部队列中。当 EventLoop 下次处理它的事件时，它会执行队列中的那些
     * 任务/事件。这也就解释了任何的 Thread 是如何与 Channel 直接交互而无需在 ChannelHandler 中进行额外同
     * 步的。
     *
     * 注意，每个 EventLoop 都有它自已的任务队列，独立于任何其他的 EventLoop。
     *
     * EventLoop 用于调度任务的执行逻辑如下。这是 Netty 线程模型的关键组成部分。
     * （1）有一个将要在 EventLoop 执行的任务；
     * （2）在把任务传递给 execute 方法之后，执行检查以确定当前调用线程是否就是分配给 EventLoop 的那个线程；
     * （3）如果就是相同的线程，则可以在 EventLoop 中直接执行任务；
     * （4）如果线程不是 EventLoop 中的那个线程，则将任务放入队列以便 EventLoop 下一次处理它的事件时执行。
     *
     * 已经知道，不要阻塞当前 I/O 线程的重要性。这里再以另一种方式重申一次：永远不要将一个长时间运行的任务放入
     * 到执行队列中，因为它将阻塞需要在同一线程上执行的任何其他任务。如果必须要进行阻塞调用或者执行长时间运行的
     * 任务，建议使用一个专门的 EventExecutor。
     *
     * 除了这种受限的场景，如同传输所采用的不同的事件处理实现一样，所使用的线程模型也可以强烈地影响到排队的任务
     * 对整体系统性能的影响。比如，使用 Netty 可以轻松地切换到不同的传输实现，而不需要修改你的代码库。
     *
     *
     *
     * EventLoop/线程的分配
     *
     * 服务于 Channel 的 I/O 和事件的 EventLoop 包含在 EventLoopGroup 中。根据不同的传输实现，EventLoop
     * 的创建和分配方式也不同。
     *
     *
     * 1、异步传输
     *
     * 异步传输实现只使用了少量的 EventLoop（以及和它们相关联的 Thread），而且在当前的线程模型中，它们可能会
     * 被多个 Channel 所共享。这使得可以通过尽可能少量的 Thread 来支撑大量的 Channel，而不是每个 Channel
     * 分配一个 Thread。
     *
     * 比如，有一个 EventLoopGroup，它具有 3 个固定大小的 EventLoop（每个 EventLoop 都由一个 Thread 支撑）。
     * 在创建 EventLoopGroup 时就直接分配了 EventLoop（以及支撑它们 的 Thread），以确保在需要时它们是可用的。
     *
     * 对于非阻塞传输（如 NIO 和 AIO）的 EventLoop 分配方式，具体过程如下：
     * （1）所有的 EventLoop 都由这个 EventLoopGroup 分配。有 3 个正在使用的 EventLoop；
     * （2）每个 EventLoop 将处理分配给它的所有的 Channel 的所有事件和任务。每个 EventLoop 都和一个 Thread
     * 相关联；
     * （3）EventLoopGroup 将为每个新创建的 Channel 分配一个 EventLoop。在每个 Channel 的整个生命周期内，
     * 所有的操作都将由相同的 Thread 执行。
     *
     * EventLoopGroup 负责为每个新创建的 Channel 分配一个 EventLoop。在当前实现中， 使用顺序循环（round-robin，
     * 即 轮询）的方式进行分配以获取一个均衡的分布，并且相同的 EventLoop 可能会被分配给多个 Channel（这一点
     * 在将来的版本中可能会改变）。
     *
     * 一旦一个 Channel 被分配给一个 EventLoop，它将在它的整个生命周期中都使用这个 EventLoop（以及相关联的
     * Thread）。请牢记这一点，因为它可以使你从担忧你的 ChannelHandler 实现中的线程安全和同步问题中解脱出来。
     *
     * 另外，需要注意的是，EventLoop 的分配方式对 ThreadLocal 的使用的影响。因为一个 EventLoop 通常会被用于
     * 支撑多个 Channel，所以对于所有相关联的 Channel 来说，ThreadLocal 都将是一样的。这使得它对于实现状态追
     * 踪等功能来说是个糟糕的选择。然而，在一些无状态的上下文中，它仍然可以被用于在多个 Channel 之间共享一些重度
     * 的或者代价昂贵的对象，甚至是事件。
     *
     *
     * 2、阻塞传输
     *
     * 用于像 OIO（旧的阻塞 I/O）这样的其他传输的设计略有不同。
     *
     * 对于阻塞传输（如 OIO）的 EventLoop 分配方式，具体过程如下：
     * （1）所有的 EventLoop 都由这个 EventLoopGroup 分配。每个新的 Channel 都将被分配一个新的 EventLoop；
     * （2）分配给 Channel 的 EventLoop 将用于执行它所有的事件和任务；
     * （3）Channel 将绑定到 EventLoop。
     *
     * 这里每一个 Channel 都将被分配给一个 EventLoop（以及它的 Thread）。如果你开发的应用程序使用过 java.io
     * 包中的阻塞 I/O 实现，可能就遇到过这种模型。
     *
     * 但是，正如同之前一样，得到的保证是每个 Channel 的 I/O 事件都将只会被一个 Thread（用于支撑该 Channel 的
     * EventLoop 的那个 Thread）处理。这也是另一个 Netty 设计一致性的例子，它（这种设计上的一致性）对 Netty
     * 的可靠性和易用性做出了巨大贡献。
     */
    public static void main(String[] args) {

    }

}
