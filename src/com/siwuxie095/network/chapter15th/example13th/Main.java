package com.siwuxie095.network.chapter15th.example13th;

/**
 * @author Jiajing Li
 * @date 2020-12-06 10:45:33
 */
public class Main {

    /**
     * Finagle 是如何工作的
     *
     * Finagle 的内部结构是非常模块化的。组件都是先独立编写，然后再堆叠在一起。根据所提供的配置，每个组件都可以
     * 被换入或者换出。例如，所有的跟踪器都实现了相同的接口，因此可以创建一个跟踪器用来将跟踪数据发送到本地文件、
     * 保存在内存中并暴露一个读取端点，或者将它写出到网络。
     *
     * 在 Finagle 栈的底部是 Transport 层。这个类表示了一个能够被异步读取和写入的对象流。Transport 被实现为
     * Netty 的 ChannelHandler，并被插入到了 ChannelPipeline 的尾端。来自网络的消息在被 Netty 接收之后，
     * 将经由 ChannelPipeline，在那里它们将被编解码器解释，并随后被发送到 Finagle 的 Transport 层。从那里
     * Finagle 将会从 Transport 层读取消息，并且通过它自己的栈发送消息。
     *
     * 对于客户端的连接，Finagle 维护了一个可以在其中进行负载均衡的传输（Transport）池。根据所提供的连接池的语
     * 义，Finagle 将从 Netty 请求一个新连接或者复用一个现有的连接。当请求新连接时，将会根据客户端的编解码器创
     * 建一个 Netty 的 ChannelPipeline。额外的用于统计、日志记录以及 SSL 的 ChannelHandler 将会被添加到该
     * ChannelPipeline 中。该连接随后将会被递给一个 Finagle 能够写入和读取的 ChannelTransport。
     *
     * 在服务器端，创建了一个 Netty 服务器，然后向其提供一个管理编解码器、统计、超时以及日志记录的
     * ChannelPipelineFactory。位于服务器 ChannelPipeline 尾端的 ChannelHandler 是一个 Finagle 的桥接
     * 器。该桥接器将监控新的传入连接，并为每一个传入连接创建一个新的 Transport。该 Transport 将在新的 Channel
     * 被递交给某个服务器的实现之前对其进行包装。随后从 ChannelPipeline 读取消息，并将其发送到已实现的服务器实例。
     *
     * 另外：部分摘要代码，可参考 PDF。
     */
    public static void main(String[] args) {

    }

}
