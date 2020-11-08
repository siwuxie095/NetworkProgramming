package com.siwuxie095.network.chapter3rd.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-08 18:58:28
 */
public class Main {

    /**
     * Channel、EventLoop 和 ChannelFuture
     *
     * Channel、EventLoop 和 ChannelFuture 可以被认为是 Netty 网络抽象的代表：
     * （1）Channel：Socket 套接字；
     * （2）EventLoop：控制流、多线程处理、并发；
     * （3）ChannelFuture：异步通知。
     *
     *
     *
     * Channel 接口
     *
     * 基本的 I/O 操作（bind()、connect()、read()和 write()）依赖于底层网络传输所提供的原语。在基于 Java 的网络编程
     * 中，其基本的构造是 class Socket。Netty 的 Channel 接口所提供的 API，大大地降低了直接使用 Socket 类的复杂性。
     *
     * 此外，Channel 也是拥有许多定义的、专门化实现的广泛类层次结构的根，下面是一个简短的部分清单（部分实现类）：
     * （1）EmbeddedChannel；
     * （2）LocalServerChannel；
     * （3）NioDatagramChannel；
     * （4）NioSctpChannel；
     * （5）NioSocketChannel。
     *
     *
     *
     * EventLoop 接口
     *
     * EventLoop 定义了 Netty 的核心抽象，用于处理连接的生命周期中所发生的事件。
     *
     *
     * Channel、EventLoop、Thread 以及 EventLoopGroup 之间的关系：
     * （1）一个 EventLoopGroup 包含一个或者多个 EventLoop；
     * （2）一个 EventLoop 在它的生命周期内只和一个 Thread 绑定；
     * （3）所有由 EventLoop 处理的 I/O 事件都将在它专有的 Thread 上被处理；
     * （4）一个 Channel 在它的生命周期内只注册于一个 EventLoop；
     * （5）一个 EventLoop 可能会被分配给一个或多个 Channel。
     *
     * 注意，在这种设计中，一个给定 Channel 的 I/O 操作都是由相同的 Thread 执行的，实际上消除了对于同步的需要。
     *
     *
     *
     * ChannelFuture 接口
     *
     * Netty 中所有的 I/O 操作都是异步的。因为一个操作可能不会立即返回，所以需要一种用于在之后的某个时间点确定其结果的方法。
     * 为此，Netty 提供了 ChannelFuture接口，其 addListener() 方法注册了一个 ChannelFutureListener，以便在某个操作
     * 完成时（无论是否成功）得到通知。
     *
     *
     * 关于 ChannelFuture 的更多讨论：
     *
     * 可以将 ChannelFuture 看作是将来要执行的操作的结果的占位符。它究竟什么时候被执行则可能取决于若干的因素，因此不可能
     * 准确地预测，但是可以肯定的是它将会被执行。此外，所有属于同一个 Channel 的操作都被保证其将以它们被调用的顺序被执行。
     */
    public static void main(String[] args) {

    }

}
