package com.siwuxie095.network.chapter2nd.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-07 14:01:16
 */
public class Main {

    /**
     * 编写 Echo 服务器
     *
     * 所有的 Netty 服务器都需要以下两部分：
     * （1）至少一个 ChannelHandler，该组件实现了服务器对从客户端接收的数据的处理，即 它的业务逻辑。
     * （2）引导，这是配置服务器的启动代码。至少，它会将服务器绑定到它要监听连接请求的端口上。
     *
     *
     *
     * ChannelHandler 和业务逻辑
     *
     * ChannelHandler 是一个接口族的父接口，它的实现负责接收并响应事件通知。在 Netty 应用程序中，所有的数据处理
     * 逻辑都包含在这些核心抽象的实现中。
     *
     * 因为 Echo 服务器会响应传入的消息，所以它需要实现 ChannelInboundHandler 接口，用来定义响应入站事件的方法。
     * 这个简单的应用程序只需要用到少量的这些方法，所以继承 ChannelInboundHandlerAdapter 类也就足够了，它提供了
     * ChannelInboundHandler 的默认实现。
     *
     * 这里感兴趣的方法是：
     * （1）channelRead()：对于每个传入的消息都要调用；
     * （2）channelReadComplete()：通知 ChannelInboundHandler 最后一次对 channelRead() 的调用是当前批量读
     * 取中的最后一条消息；
     * （3）exceptionCaught()：在读取操作期间，有异常抛出时会调用；
     *
     * 以 EchoServerHandler 为例，即是 Echo 服务器的 ChannelHandler 实现。
     *
     * ChannelInboundHandlerAdapter 有一个直观的 API，并且它的每个方法都可以被重写以挂钩到事件生命周期的恰当点
     * 上。因为需要处理所有接收到的数据，所以重写了 channelRead() 方法。在这个服务器应用程序中，将数据简单地回送
     * 给了远程节点。
     *
     * 重写 exceptionCaught()方法允许对 Throwable 的任何子类型做出反应，在这里只是记录了异常并关闭了连接。虽然
     * 一个更加完善的应用程序也许会尝试从异常中恢复，但在这个场景下，只是通过简单地关闭连接来通知远程节点发生了错误。
     *
     * 如果不捕获异常，会发生什么呢？
     *
     * 每个 Channel 都拥有一个与之相关联的 ChannelPipeline，其持有一个 ChannelHandler 的实例链。在默认的情况
     * 下，ChannelHandler 会把对它的方法调用转发给链中的下一个 ChannelHandler。因此，如果 exceptionCaught()
     * 方法没有被该链中的某处实现，那么所接收的异常将会被传递到 ChannelPipeline 的尾端并被记录。为此，应用程序应
     * 该提供至少有一个实现了 exceptionCaught() 方法的 ChannelHandler。
     *
     * 关键点：
     * （1）针对不同类型的事件来调用 ChannelHandler；
     * （2）应用程序通过实现或者扩展 ChannelHandler 来挂钩到事件的生命周期，并且提供自定义的应用程序逻辑；
     * （3）在架构上，ChannelHandler 有助于保持业务逻辑与网络处理代码的分离。这简化了开发过程，因为代码必须不断地
     * 演化以响应不断变化的需求。
     *
     *
     *
     * 引导服务器
     *
     * 在讨论过由 EchoServerHandler 实现的核心业务逻辑之后，现在可以探讨引导服务器本身的过程了，具体涉及以下内容：
     * （1）绑定到 服务器将在其上监听并接受传入连接请求 的端口（注意断句）；
     * （2）配置 Channel，以将有关的入站消息通知给 EchoServerHandler 实例。
     *
     * PS：传输，术语解释如下：
     * 在网络协议的标准多层视图中，传输层提供了端到端的或者主机到主机的通信服务。因特网通信是建立在 TCP 传输之上的。
     * 除了一些由 Java NIO 实现提供的服务器端性能增强之外，NIO 传输大多数时候指的就是 TCP 传输。
     *
     * 以 EchoServer 为例，如下：
     *
     * 先创建了一个 ServerBootstrap 实例。因为正在使用的是 NIO 传输，所以指定了 NioEventLoopGroup 来接受和处
     * 理新的连接，并且将 Channel 的类型指定为 NioServerSocketChannel。在此之后，将本地地址设置为一个具有选定
     * 端口的 InetSocketAddress。服务器将绑定到这个地址以监听新的连接请求。
     *
     * 再使用一个特殊的类 ChannelInitializer。这是关键。当一个新的连接被接受时，一个新的子 Channel 将会被创建，
     * 而 ChannelInitializer 将会把 EchoServerHandler 的实例添加到该 Channel 的 ChannelPipeline 中。这个
     * ChannelHandler 将会收到有关入站消息的通知。
     *
     * 虽然 NIO 是可伸缩的，但是其适当的尤其是关于多线程处理的配置并不简单。Netty 的设计封装了大部分的复杂性。
     *
     * 接下来绑定服务器，并等待绑定完成（对 sync()方法的调用将导致当前 Thread 阻塞，一直到绑定操作完成为止）。然
     * 后该应用程序将会阻塞等待直到服务器的 Channel 关闭（因为在 Channel 的 CloseFuture 上调用了 sync()方法）。
     * 最后，可以关闭 EventLoopGroup，并释放所有的资源，包括所有被创建的线程。
     *
     * 这个示例使用了 NIO，得益于它的可扩展性和彻底的异步性，它是目前使用最广泛的传输。但是也可以使用一个不同的传输
     * 实现。如果想要在自己的服务器中使用 OIO 传输，将需要指定 OioServerSocketChannel 和 OioEventLoopGroup。
     *
     * 回顾服务器实现中的重要步骤，可以得出服务器的主要代码组件：
     * （1）EchoServerHandler 实现了业务逻辑；
     * （2）start() 方法引导了服务器；
     *
     * 引导过程中所需要的步骤如下：
     * （1）创建一个 ServerBootstrap 的实例以引导和绑定服务器；
     * （2）创建并分配一个 NioEventLoopGroup 实例以进行事件的处理，如接受新连接以及读/写数据；
     * （3）指定服务器绑定的本地的 InetSocketAddress；
     * （4）使用一个 EchoServerHandler 的实例初始化每一个新的 Channel；
     * （5）调用 ServerBootstrap.bind() 方法以绑定服务器。
     */
    public static void main(String[] args) {

    }

}
