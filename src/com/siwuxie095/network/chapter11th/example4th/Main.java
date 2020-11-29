package com.siwuxie095.network.chapter11th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-29 14:20:35
 */
public class Main {

    /**
     * 空闲的连接和超时
     *
     * 到目前为止，这里的讨论都集中在 Netty 通过专门的编解码器和处理器对 HTTP 的变型 HTTPS 和 WebSocket 的
     * 支持上。只要你有效地管理你的网络资源，这些技术就可以使得你的应用程序更加高效、易用和安全。所以，下面就来
     * 探讨下首先需要关注的 —— 连接管理吧。
     *
     * 检测空闲连接以及超时对于及时释放资源来说是至关重要的。由于这是一项常见的任务，Netty 特地为它提供了几个
     * ChannelHandler 实现（用于空闲连接以及超时的 ChannelHandler）。如下：
     * （1）IdleStateHandler：
     * 当连接空闲时间太长时，将会触发一个 IdleStateEvent 事件。然后，你可以通过在你的 ChannelInboundHandler
     * 中重写 userEventTriggered() 方法来处理该 IdleStateEvent 事件。
     *
     * （2）ReadTimeoutHandler：
     * 如果在指定的时间间隔内没有收到任何的入站数据，则抛出一个 ReadTimeoutException 并关闭对应的 Channel。
     * 可以通过重写你的 ChannelHandler 中的 exceptionCaught() 方法来检测该 ReadTimeoutException。
     *
     * （3）WriteTimeoutHandler：
     * 如果在指定的时间间隔内没有任何出站数据写入，则抛出一个 WriteTimeoutException 并关闭对应的 Channel。
     * 可以通过重写你的 ChannelHandler 的 exceptionCaught() 方法检测该 WriteTimeoutException。
     *
     * 不妨来看看在实践中使用得最多的 IdleStateHandler。以 IdleStateHandlerInitializer 为例，展示了当
     * 使用通常的发送心跳消息到远程节点的方法时，如果在 60 秒之内没有接收或者发送任何的数据，将如何得到通知。
     * 如果没有响应，则连接会被关闭。
     *
     * 这个示例演示了如何使用 IdleStateHandler 来测试远程节点是否仍然还活着，并且在它失活时通过关闭连接来
     * 释放资源。
     *
     * 如果连接超过 60 秒没有接收或者发送任何的数据，那么 IdleStateHandler 将会使用一个 IdleStateEvent
     * 事件来调用 fireUserEventTriggered() 方法。HeartbeatHandler 实现了 userEventTriggered() 方法，
     * 如果这个方法检测到 IdleStateEvent 事件，它将会发送心跳消息，并且添加一个将在发送操作失败时关闭该连接
     * 的 ChannelFutureListener。
     */
    public static void main(String[] args) {

    }

}
