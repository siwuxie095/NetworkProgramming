package com.siwuxie095.network.chapter4th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-10 08:19:26
 */
public class Main {

    /**
     * 内置的传输
     *
     * Netty 内置了一些可开箱即用的传输。因为并不是所有的传输都支持每一种协议，所以你必须选择一个和你的应用程序
     * 所使用的协议相容的传输。
     *
     * Netty 提供的所有传输如下：
     *
     * （1）NIO
     *
     * 位于 io.netty.channel.socket.nio 包下。使用 java.nio.channels 包作为基础 —— 种基于选择器的方式。
     *
     * （2）Epoll
     *
     * 位于 io.netty.channel.epoll 包下。由 JNI 驱动的 epoll() 和非阻塞 IO。这个传输支持只有在 Linux 上
     * 可用的多种特性，如 SO_REUSEPORT，比 NIO 传输更快，而且是完全非阻塞的。
     *
     * PS：这个是 Netty 特有的实现，更加适配 Netty 现有的线程模型，具有更高的性能以及更低的垃圾回收压力， 详
     * 见 https://github.com/netty/netty/wiki/Native-transports
     *
     * （3）OIO
     *
     * 位于 io.netty.channel.socket.oio 包下。使用 java.net 包作为基础 —— 使用阻塞流。
     *
     * （4）Local
     *
     * 位于 io.netty.channel.local 包下。可以在 VM 内部通过管道进行通信的本地传输。
     *
     * （5）Embedded
     *
     * 位于 io.netty.channel.embedded 包下。Embedded 传输，允许使用 ChannelHandler 而又不需要一个真正
     * 的基于网络的传输。这在测试你的 ChannelHandler 实现时非常有用。
     *
     *
     *
     * NIO：非阻塞 I/O
     *
     * NIO 提供了一个所有 I/O 操作的全异步的实现。它利用了自 NIO 子系统被引入 JDK 1.4 时便可用的基于选择器
     * 的 API。
     *
     * 选择器背后的基本概念是充当一个注册表，可以请求在 Channel 的状态发生变化时得到通知。可能的状态变化有：
     * （1）新的 Channel 已被接受并且就绪；
     * （2）Channel 连接已经完成；
     * （3）Channel 有已经就绪的可供读取的数据；
     * （4）Channel 可用于写数据。
     *
     * 选择器运行在一个检查状态变化并对其做出相应响应的线程上，在应用程序对状态的改变做出响应之后，选择器将会
     * 被重置，并将重复这个过程。
     *
     * 如下常量值代表了由 java.nio.channels.SelectionKey 定义的位模式。这些位模式可以组合起来定义一组应用
     * 程序正在请求通知的状态变化集。
     * （1）OP_ACCEPT：请求在接受新连接并创建 Channel 时获得通知。
     * （2）OP_CONNECT：请求在建立一个连接时获得通知。
     * （3）OP_READ：请求当数据已经就绪，可以从 Channel 中读取时获得通知。
     * （4）OP_WRITE：请求当可以向 Channel 中写更多的数据时获得通知。这处理了套接字缓冲区被完全填满时的情况，
     * 这种情况通常发生在数据的发送速度比远程节点可处理的速度更快的时候。
     *
     * 对于 Netty 所有的传输实现都共有的用户级别 API 完全地隐藏了这些 NIO 的内部细节。
     *
     * 关于零拷贝：
     * 零拷贝（zero-copy）是一种目前只有在使用 NIO 和 Epoll 传输时才可使用的特性。它可以快速高效地将数据从
     * 文件系统移动到网络接口，而不需要将其从内核空间复制到用户空间，其在像 FTP 或者 HTTP 这样的协议中可以显
     * 著地提升性能。但是，并不是所有的操作系统都支持这一特性。特别地，它对于实现了数据加密或者压缩的文件系统
     * 是不可用的 —— 只能传输文件的原始内容。反过来说，传输已被加密的文件则不是问题。
     *
     *
     *
     * Epoll：用于 Linux 的本地非阻塞传输
     *
     * Netty 的 NIO 传输基于 Java 提供的异步/非阻塞网络编程的通用抽象。 虽然这保证了 Netty 的非阻塞 API
     * 可以在任何平台上使用，但它也包含了相应的限制，因为 JDK 为了在所有系统上提供相同的功能，必须做出妥协。
     *
     * Linux 作为高性能网络编程的平台，其重要性与日俱增，这催生了大量先进特性的开发，其中就包括 epoll，一个
     * 高度可扩展的 I/O 事件通知特性。这个 API 自 Linux 内核版本 2.5.44(2002) 被引入，提供了比旧的 POSIX
     * select 和 poll 系统调用更好的性能，同时现在也是 Linux 上非阻塞网络编程的事实标准。Linux JDK NIO
     * API 使用了这些 epoll 调用。
     *
     * PS：关于 Linux 的 epoll，可参考 https://linux.die.net/man/4/epoll
     *
     * Netty 为 Linux 提供了一组 NIO API，其以一种和它本身的设计更加一致的方式使用 epoll，并且以一种更加
     * 轻量的方式使用中断。如果你的应用程序旨在运行于 Linux 系统，那么请考虑利用这个版本的传输。你将发现，在
     * 高负载下它的性能要优于 JDK 的 NIO 实现。
     *
     * PS：JDK 的实现是水平触发，而 Netty 的（默认的）是边沿触发。有关的详细信息参见 epoll 在维基百科上的
     * 解释 https://en.wikipedia.org/wiki/Epoll，详见 Triggering modes 部分。
     *
     * 以 NettyEpollServer 为例，展示了相关 API 的用法。
     *
     *
     *
     * OIO：旧的阻塞 I/O
     *
     * Netty 的 OIO 传输实现代表了一种折中：它可以通过常规的传输 API 使用，但是由于它是建立在 java.net 包
     * 的阻塞实现之上的，所以它不是异步的。但是，它仍然非常适合于某些用途。
     *
     * 例如，你可能需要移植使用了一些进行阻塞调用的库（如 JDBC）的遗留代码，而将逻辑转换为非阻塞，可能也是不
     * 切实际的。相反，你可以在短期内使用 Netty 的 OIO 传输，然后再将你的代码移植到纯粹的异步传输上。
     *
     * 下面来看一看怎么做。
     *
     * 在 java.net API 中，你通常会有一个用来接受到达正在监听的 ServerSocket 的新连接的线程。会创建一个新
     * 的和远程节点进行交互的套接字，并且会分配一个新的用于处理相应通信流量的线程。这是必需的，因为某个指定套
     * 接字上的任何 I/O 操作在任意的时间点上都可能会阻塞。使用单个线程来处理多个套接字，很容易导致一个套接字
     * 上的阻塞操作也捆绑了所有其他的套接字。
     *
     * 有了这个之后背景，Netty 是如何能够使用和用于异步传输相同的 API 来支持 OIO 的呢。答案就是，Netty 利用
     * 了 SO_TIMEOUT 这个 Socket 标志，它指定了等待一个 I/O 操作完成的最大毫秒数。如果操作在指定的时间间隔
     * 内没有完成，则将会抛出一个 SocketTimeoutException。Netty 将捕获这个异常并继续处理循环。在 EventLoop
     * 下一次运行时，它将再次尝试。这实际上也是类似于 Netty 这样的异步框架能够支持 OIO 的唯一方式。
     *
     * PS：这种方式的一个问题是，当一个 SocketTimeoutException 被抛出时填充栈跟踪所需要的时间，其对于性能
     * 来说代价很大。
     *
     *
     *
     * 用于 JVM 内部通信的 Local 传输
     *
     * Netty 提供了一个 Local 传输，用于在同一个 JVM 中运行的客户端和服务器程序之间的异步通信。同样，这个
     * 传输也支持对于 Netty 所有传输实现都共同的 API。
     *
     * 在这个传输中，和服务器 Channel 相关联的 SocketAddress 并没有绑定物理网络地址。相反，只要服务器还在
     * 运行，它就会被存储在注册表里，并在 Channel 关闭时注销。因为这个传输并不接受真正的网络流量，所以它并不
     * 能够和其他传输实现进行互操作。因此，客户端希望连接到（在同一个 JVM 中）使用了这个传输的服务器端时也必
     * 须使用它。除了这个限制，它的使用方式和其他的传输一模一样。
     *
     *
     *
     * Embedded 传输
     *
     * Netty 提供了一种额外的传输，使得可以将一组 ChannelHandler 作为帮助器类嵌入到其他的 ChannelHandler
     * 内部。通过这种方式，可以扩展一个 ChannelHandler 的功能，而又不需要修改其内部代码。
     *
     * 不足为奇的是，Embedded 传输的关键是一个被称为 EmbeddedChannel 的具体的 Channel 实现。
     */
    public static void main(String[] args) {

    }

}
