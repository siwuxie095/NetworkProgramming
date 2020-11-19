package com.siwuxie095.network.chapter7th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-19 07:49:44
 */
@SuppressWarnings("all")
public class Main {

    /**
     * EventLoop 接口
     *
     * 运行任务来处理在连接的生命周期内发生的事件是任何网络框架的基本功能。与之相应的编程上的构造通常被称为事件循环，
     * 一个 Netty 使用了 io.netty.channel.EventLoop 接口来适配的术语。
     *
     * 以 EventLoopExamples 为例，说明了事件循环的基本思想，其中每个任务都是一个 Runnable 的实例。
     *
     * Netty 的 EventLoop 是协同设计的一部分，它采用了两个基本的 API：并发和网络编程。
     *
     * 首先，io.netty.util.concurrent 包构建在 JDK 的 java.util.concurrent 包上，用来提供线程执行器。其次，
     * io.netty.channel 包中的类，为了与 Channel 的事件进行交互，扩展了这些接口/类。
     *
     * 在这个模型中，一个 EventLoop 将由一个永远都不会改变的 Thread 驱动，同时任务（Runnable 或者 Callable）
     * 可以直接提交给 EventLoop 实现，以立即执行或者调度执行。根据配置和可用核心的不同，可能会创建多个 EventLoop
     * 实例用以优化资源的使用，并且单个 EventLoop 可能会被指派用于服务多个 Channel。
     *
     * 需要注意的是，Netty 的 EventLoop 在继承了 ScheduledExecutorService 的同时，只定义了一个方法，parent()。
     * 这个方法，如下面的代码片断所示，用于返回到当前 EventLoop 实现的实例所属的 EventLoopGroup 的引用。
     *
     * public interface EventLoop extends OrderedEventExecutor, EventLoopGroup {
     *     @Override
     *     EventLoopGroup parent();
     * }
     *
     * PS：这个方法重写了 EventExecutor 的 EventExecutorGroup parent() 方法。
     *
     * 事件/任务的执行顺序：事件和任务是以先进先出（FIFO）的顺序执行的。这样可以通过保证字节内容总是按正确的顺序被
     * 处理，消除潜在的数据损坏的可能性。
     *
     *
     *
     * Netty 4 中的 I/O 和事件处理
     *
     * 由 I/O 操作触发的事件将流经安装了一个或者多个 ChannelHandler 的 ChannelPipeline。传播这些事件的方法调
     * 用可以随后被 ChannelHandler 所拦截，并且可以按需地处理事件。
     *
     * 事件的性质通常决定了它将被如何处理。它可能将数据从网络栈中传递到你的应用程序中，或者进行逆向操作，或者执行一
     * 些截然不同的操作。但是事件的处理逻辑必须足够的通用和灵活，以处理所有可能的用例。因此，在 Netty 4 中，所有的
     * I/O 操作和事件都由已经被分配给了 EventLoop 的那个 Thread 来处理（这里使用的是 "来处理" 而不是 "来触发"，
     * 其中写操作是可以从外部的任意线程触发的）。
     *
     * 这不同于 Netty 3 中所使用的模型。下面将讨论这个早期的模型以及它被替换的原因。
     *
     *
     *
     * Netty 3 中的 I/O 操作
     *
     * 在以前的版本中所使用的线程模型只保证了入站（之前称为上游）事件会在所谓的 I/O 线程（对应于 Netty 4 中的
     * EventLoop）中执行。所有的出站（下游）事件都由调用线程处理，其可能是 I/O 线程也可能是别的线程。开始看起来
     * 这似乎是个好主意，但是已经被发现是有问题的，因为需要在 ChannelHandler 中对出站事件进行仔细的同步。简而言
     * 之，不可能保证多个线程不会在同一时刻尝试访问出站事件。例如，如果你通过在不同的线程中调用 Channel.write()
     * 方法，针对同一个 Channel 同时触发出站的事件，就会发生这种情况。
     *
     * 当出站事件触发了入站事件时，将会导致另一个负面影响。当 Channel.write() 方法导致异常时，需要生成并触发一
     * 个 exceptionCaught 事件。但是在 Netty 3 的模型中，由于这是一个入站事件，需要在调用线程中执行代码，然后
     * 将事件移交给 I/O 线程去执行，然而这将带来额外的上下文切换。
     *
     * Netty 4 中所采用的线程模型，通过在同一个线程中处理某个给定的 EventLoop 中所产生的所有事件，解决了这个问
     * 题。这提供了一个更加简单的执行体系架构，并且消除了在多个 ChannelHandler 中进行同步的需要（除了任何可能需
     * 要在多个 Channel 中共享的）。
     */
    public static void main(String[] args) {

    }

}
