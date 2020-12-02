package com.siwuxie095.network.chapter13th.example6th;

/**
 * @author Jiajing Li
 * @date 2020-12-02 08:21:42
 */
public class Main {

    /**
     * 编写广播者
     *
     * Netty 提供了大量的类来支持 UDP 应用程序的编写。如下列出了将要使用的主要的消息容器以及 Channel 类型。
     * （1）interface AddressedEnvelope<M, A extends SocketAddress> extends ReferenceCounted
     * 定义一个消息，其包装了另一个消息并带有发送者和接收者地址。其中 M 是消息类型，A 是 地址类型。
     *
     * （2）class DefaultAddressedEnvelope<M, A extends SocketAddress> implements AddressedEnvelope<M,A>
     * 提供了 interface AddressedEnvelope 的默认实现。
     *
     * （3）class DatagramPacket extends DefaultAddressedEnvelope<ByteBuf, InetSocketAddress> implements ByteBufHolder
     * 扩展了 DefaultAddressedEnvelope 以使用 ByteBuf 作为消息数据容器。
     *
     * （4）interface DatagramChannel extends Channel
     * 扩展了 Netty 的 Channel 抽象以支持 UDP 的多播组管理。
     *
     * （5）class NioDatagramChannel extends AbstractNioMessageChannel implements DatagramChannel
     * 定义了一个能够发送和接收 AddressedEnvelope 消息的 Channel 类型。
     *
     * Netty 的 DatagramPacket 是一个简单的消息容器，DatagramChannel 实现用它来和远程节点通信。类似于先前类比中
     * 的明信片，它包含了接收者（和可选的发送者）的地址以及消息的有效负载本身。
     *
     * 要将 LogEvent 消息转换为 DatagramPacket，就需要一个编码器。但是没有必要从头开始编写，这里将扩展 Netty 的
     * MessageToMessageEncoder。
     *
     * 以 LogEventEncoder 为例，即为编码器。
     *
     * 以 LogEventBroadcaster 为例，即为广播者。
     *
     * 该 LogEventBroadcaster 的 ChannelPipeline 的一个高级别视图是这样，首先从本地读取数据，所有的将要被传输的
     * 数据都被封装在了 LogEvent 消息中。LogEventBroadcaster 将把这些写入到 Channel 中，并通过 ChannelPipeline
     * 发送它们，在那里它们将会被转换（编码）为 DatagramPacket 消息。最后，他们都将通过 UDP 被广播，并由远程节点
     * （监视器）所捕获。
     *
     * LogEventBroadcaster 中完成了引导服务器的工作，其包括设置各种各样的 ChannelOption，以及在 ChannelPipeline
     * 中安装所需要的 ChannelHandler。
     *
     * 这样就完成了该应用程序的广播者组件。对于初始测试，你可以使用 netcat 程序。在 UNIX/Linux 系统中，你能发现它已
     * 经作为 nc 被预装了。用于 Windows 的版本可以从 https://nmap.org/ncat/ 获取（PS：也可以使用 scoop install
     * netcat 进行安装）。
     *
     * netcat 非常适合于对这个应用程序进行基本的测试。它只是监听某个指定的端口，并且将所有接收到的数据打印到标准输出。
     * 可以通过下面所示的方式，将其设置为监听 UDP 端口 8888 上的数据。
     *
     * nc -l -u -p 8888
     *
     * 运行 LogEventBroadcaster 中的 main 的方法，并指定参数为 8888 /var/log/system.log，用来设置端口和日志文
     * 件位置。一旦这个进程运行起来，它就会广播任何新被添加到该日志文件中的日志消息。
     *
     * 使用 netcat 对于测试来说是足够了，但是它并不适合于生产系统。这也就有了应用程序的第二个部分，后续将要实现的广播
     * 监视器。
     */
    public static void main(String[] args) {

    }

}
