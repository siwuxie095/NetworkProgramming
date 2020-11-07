package com.siwuxie095.network.chapter2nd.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-07 22:32:09
 */
public class Main {

    /**
     * 编写 Echo 客户端
     *
     * Echo 客户端将会做这些事情：
     * （1）连接到服务器；
     * （2）发送一个或者多个消息；
     * （3）对于每个消息，等待并接收从服务器发回的相同的消息；
     * （4）关闭连接。
     *
     * 和服务器一样，编写客户端所涉及的两个主要代码部分也是业务逻辑和引导。
     *
     *
     *
     * 通过 ChannelHandler 实现客户端逻辑
     *
     * 如同服务器，客户端将拥有一个用来处理数据的 ChannelInboundHandler。在这个场景下，具体将扩展
     * SimpleChannelInboundHandler 类以处理所有必须的任务。
     *
     * 以 EchoClientHandler 为例，需要重写下面的方法：
     * （1）channelActive()：在到服务器的连接已经建立之后将被调用；
     * （2）channelRead0()：当从服务器接收到一条消息时被调用；
     * （3）exceptionCaught()：在处理过程中引发异常时被调用。
     *
     * 首先，重写了 channelActive()方法，其将在一个连接建立时被调用。这确保了数据将会被尽可能快地写入服务器，
     * 其在这个场景下是一个编码了字符串 "Netty rocks!" 的字节缓冲区。
     *
     * 接下来，重写了 channelRead0()方法。每当接收数据时，都会调用这个方法。需要注意的是，由服务器发送的消息
     * 可能会被分块接收。也就是说，如果服务器发送了 5 字节，那么不能保证这 5 字节会被一次性接收。即使是对于这
     * 么少量的数据，channelRead0() 方法也可能会被调用两次，第一次使用一个持有 3 字节的 ByteBuf（Netty 的
     * 字节容器），第二次使用一个持有 2 字节的 ByteBuf。作为一个面向流的协议，TCP 保证了字节数组将会按照服务
     * 器发送它们的顺序被接收。
     *
     * 重写的第三个方法是 exceptionCaught()。在这里记录 Throwable，并关闭 Channel。在这个场景下，是终止到
     * 服务器的连接。
     *
     * SimpleChannelInboundHandler 与 ChannelInboundHandler：
     *
     * 为什么在 EchoClientHandler 使用的是 SimpleChannelInboundHandler，而不是在 EchoServerHandler
     * 中所使用的 ChannelInboundHandlerAdapter 呢？这和两个因素的相互作用有关：业务逻辑如何处理消息以及
     * Netty 如何管理资源。
     *
     * 在 EchoClientHandler 中，当 channelRead0() 方法完成时，你已经有了传入消息，并且已经处理完它了。当
     * 该方法返回时，SimpleChannelInboundHandler 负责释放指向保存该消息的 ByteBuf 的内存引用。
     *
     * 在 EchoServerHandler 中，仍然需要将传入消息回送给发送者，而 write() 操作是异步的，直到 channelRead()
     * 方法返回后可能仍然没有完成。为此，EchoServerHandler 扩展了 ChannelInboundHandlerAdapter，其在这个
     * 时间点上不会释放消息。消息在 EchoServerHandler 的 channelReadComplete() 方法中，当 writeAndFlush()
     * 方法被调用时被释放。
     *
     *
     *
     * 引导客户端
     *
     * 以 EchoClient 为例，引导客户端类似于引导服务器，不同的是，客户端是使用主机和端口参数来连接远程地址，
     * 也就是这里的 Echo 服务器的地址，而不是绑定到一个一直被监听的端口。
     *
     * 和之前一样，这里使用了 NIO 传输。注意，可以在客户端和服务器上分别使用不同的传输。 例如，在服务器端
     * 使用 NIO 传输，而在客户端使用 OIO 传输。
     *
     * 引导过程中所需要的步骤如下：
     * （1）为初始化客户端，创建了一个 Bootstrap 实例；
     * （2）为进行事件处理分配了一个 NioEventLoopGroup 实例，其中事件处理包括创建新的连接以及处理入站和
     * 出站数据；
     * （3）为服务器连接创建了一个 InetSocketAddress 实例；
     * （4）当连接被建立时，一个 EchoClientHandler 实例会被安装到（该 Channel 的）ChannelPipeline 中。
     * （5）在一切都设置完成后，调用 Bootstrap.connect()方法连接到远程节点。
     */
    public static void main(String[] args) {

    }

}
