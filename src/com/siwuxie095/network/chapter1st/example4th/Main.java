package com.siwuxie095.network.chapter1st.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-05 08:06:01
 */
public class Main {

    /**
     * Netty 的核心组件
     *
     * Netty 的主要构件块如下：
     * （1）Channel
     * （2）回调（Callback）
     * （3）Future
     * （4）事件（Event）和 ChannelHandler
     *
     * 这些构件块代表了不同类型的构造：资源、逻辑以及通知。应用程序将使用它们来访问网络以及流经网络的数据。
     *
     *
     *
     * Channel
     *
     * Channel 是 Java NIO 的一个基本构造。
     *
     * 它代表一个到实体（如一个硬件设备、一个文件、一个网络套接字或者一个能够执行一个或者多个不同的 I/O 操作的程序组件）的
     * 开放连接，如读操作和写操作。
     *
     * 目前，可以把 Channel 看作是传入（入站）或者传出（出站）数据的载体。因此，它可以被打开或者被关闭，连接或者断开连接。
     *
     * PS：可参考 https://docs.oracle.com/javase/8/docs/api/java/nio/channels/package-summary.html
     *
     *
     *
     * 回调
     *
     * 一个回调其实就是一个方法，一个指向已经被提供给另外一个方法的方法的引用。这使得后者（即 接受回调的方法）可以在适当的
     * 时候调用前者（即 回调方法）。回调在广泛的编程场景中都有应用，而且也是在操作完成后通知相关方最常见的方式之一。
     *
     * Netty 在内部使用了回调来处理事件。当一个回调被触发时，相关的事件可以被一个 interface -- ChannelHandler 的实现
     * 处理。以 ConnectHandler 为例，当一个新的连接已经被建立时，ChannelHandler 的 channelActive() 回调方法将会被
     * 调用，并将打印出一条信息（实际上是 ChannelInboundHandler 的 channelActive() 方法，ChannelInboundHandler
     * 是 ChannelHandler 的继承接口，但可以认为是 ChannelHandler 的 channelActive() 方法）。
     *
     *
     *
     * Future
     *
     * Future 提供了另一种在操作完成时通知应用程序的方式。这个对象可以看作是一个异步操作的结果的占位符。它将在未来的某个
     * 时刻完成，并提供对其结果的访问。
     *
     * JDK 预置了 interface java.util.concurrent.Future，但是其所提供的实现，只允许手动检查对应的操作是否已经完成，
     * 或者一直阻塞直到它完成。这是非常繁琐的，所以 Netty 提供了它自己的实现 —— ChannelFuture，用于在执行异步操作的
     * 时候使用（注意：ChannelFuture 接口继承自 Netty 自己的 Future 接口，Netty 自己的 Future 接口继承自 JDK 预置
     * 的 Future 接口）。
     *
     * ChannelFuture 提供了几种额外的方法，这些方法使得能够注册一个或者多个 ChannelFutureListener（监听器）实例。监
     * 听器的回调方法 operationComplete()，将会在对应的操作完成时被调用（如果在监听器添加到 ChannelFuture 的时候，
     * ChannelFuture 已经完成， 那么该监听器将会被直接地通知）。然后监听器可以判断该操作是成功地完成了还是出错了。如果
     * 是后者，可以检索产生的 Throwable。简而言之 ，由监听器提供的通知机制消除了手动检查对应的操作是否完成的必要。
     *
     * 每个 Netty 的出站 I/O 操作都将返回一个 ChannelFuture。也就是说，它们都不会阻塞。
     *
     * PS：再次重申：Netty 完全是异步和事件驱动的。
     *
     * 以 ConnectExample 为例，展示了一个 ChannelFuture 作为一个 I/O 操作的一部分返回，以及如何利用监听器回调。
     *
     * 这里，connect() 方法将会直接返回，而不会阻塞，该调用将会在后台完成。这究竟什么时候会发生则取决于若干的因素，但这个
     * 关注点已经从代码中抽象出来了。因为线程不用阻塞以等待对应的操作完成，所以它可以同时做其他的工作，从而更加有效地利用
     * 资源。
     *
     * 然后，要注册一个新的 ChannelFutureListener 到对 connect() 方法的调用所返回的 ChannelFuture 上。当该监听器被
     * 通知连接已经建立的时候，要检查对应的状态。如果该操作是成功的，那么将数据写到该 Channel。否则，要从 ChannelFuture
     * 中检索对应的 Throwable。
     *
     * 需要注意的是，对错误的处理完全取决于你、目标，当然也包括目前任何对于特定类型的错误施加的限制。例如，如果连接失败，
     * 你可以尝试重新连接或者建立一个到另一个远程节点的连接。如果你把 ChannelFutureListener 看作是回调的一个更加精细的
     * 版本，那么你是对的。事实上，回调和 Future 是相互补充的机制，它们相互结合，构成了 Netty 本身的关键构件块之一。
     *
     *
     *
     * 事件和 ChannelHandler
     *
     * Netty 使用不同的事件来通知状态的改变或者是操作的状态。这使得能够基于已经发生的事件来触发适当的动作。这些动作可能是：
     * （1）记录日志；
     * （2）数据转换；
     * （3）流控制；
     * （4）应用程序逻辑。
     *
     * Netty 是一个网络编程框架，所以事件是按照它们与入站或出站数据流的相关性进行分类的。可能由入站数据或者相关的状态更改
     * 而触发的事件包括：
     * （1）连接已被激活或者连接失活；
     * （2）数据读取；
     * （3）用户事件；
     * （4）错误事件。
     *
     * 出站事件是未来将会触发的某个动作的操作结果，这些动作包括：
     * （1）打开或者关闭到远程节点的连接；
     * （2）将数据写到或者冲刷到套接字。
     *
     * 每个事件都可以被分发给 ChannelHandler 类中的某个用户实现的方法。这是一个很好的将事件驱动范式直接转换为应用程序构
     * 件块的例子。
     *
     * Netty 的 ChannelHandler 为处理器提供了基本的抽象，目前可以认为每个 ChannelHandler 的实例都类似于一种为了响应
     * 特定事件而被执行的回调。Netty 提供了大量预定义的可以开箱即用的 ChannelHandler 实现，包括用于各种协议（如 HTTP、
     * SSL/TLS）的 ChannelHandler。在内部，ChannelHandler 自己也使用了事件和 Future，使得它们也成为了你的应用程序
     * 将使用的相同抽象的消费者。
     *
     *
     *
     * 把它们放在一起
     *
     * 1、Future、回调和 ChannelHandler
     *
     * Netty 的异步编程模型是建立在 Future 和回调的概念之上的，而将事件派发到 ChannelHandler 的方法则发生在更深的层次
     * 上。结合在一起，这些元素就提供了一个处理环境，使你的应用程序逻辑可以独立于任何网络操作相关的顾虑而独立地演变。这也是
     * Netty 设计方式的一个关键目标。
     *
     * 拦截操作以及高速地转换入站数据和出站数据，都只需要你提供回调或者利用操作所返回的 Future。这使得链接操作变得既简单又
     * 高效，并且促进了可重用的通用代码的编写。
     *
     * 2、选择器、事件和 EventLoop
     *
     * Netty 通过触发事件将 Selector 从应用程序中抽象出来，消除了所有本来将需要手动编写的派发代码。在内部，将会为每个
     * Channel 分配一个 EventLoop，用以处理所有事件，包括：
     * （1）注册感兴趣的事件；
     * （2）将事件派发给 ChannelHandler；
     * （3）安排进一步的动作。
     *
     * EventLoop 本身只由一个线程驱动，其处理了一个 Channel 的所有 I/O 事件，并且在该 EventLoop 的整个生命周期内都不
     * 会改变。这个简单而强大的设计消除了可能在 ChannelHandler 实现中需要进行同步的任何顾虑，因此，可以专注于提供正确的
     * 逻辑，用来在有感兴趣的数据要处理的时候执行。EventLoop 这个 API 是简单而紧凑的。
     *
     * PS：需要引入一个 jar 包（Netty/All In One）：io.netty » netty-all » 4.1.53.Final
     */
    public static void main(String[] args) {

    }

}
