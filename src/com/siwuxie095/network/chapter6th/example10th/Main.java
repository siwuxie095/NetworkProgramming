package com.siwuxie095.network.chapter6th.example10th;

/**
 * @author Jiajing Li
 * @date 2020-11-16 22:26:25
 */
public class Main {

    /**
     * 触发事件
     *
     * ChannelPipeline 的 API 公开（暴露）了用于调用入站和出站操作的附加方法。如下列出了入站操作，用于通知
     * ChannelInboundHandler 在 ChannelPipeline 中所发生的事件。
     *
     * （1）fireChannelRegistered：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelRegistered(ChannelHandlerContext) 方法。
     *
     * （2）fireChannelUnregistered：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelUnregistered(ChannelHandlerContext) 方法。
     *
     * （3）fireChannelActive：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelActive(ChannelHandlerContext) 方法。
     *
     * （4）fireChannelInactive：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelInactive(ChannelHandlerContext) 方法。
     *
     * （5）fireExceptionCaught：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * exceptionCaught(ChannelHandlerContext, Throwable) 方法。
     *
     * （6）fireUserEventTriggered：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * userEventTriggered(ChannelHandlerContext, Object) 方法。
     *
     * （7）fireChannelRead：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelRead(ChannelHandlerContext, Object msg) 方法。
     *
     * （8）fireChannelReadComplete：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelReadComplete(ChannelHandlerContext) 方法。
     *
     * （9）fireChannelWritabilityChanged：调用 ChannelPipeline 中下一个 ChannelInboundHandler 的
     * channelWritabilityChanged(ChannelHandlerContext) 方法。
     *
     *
     * 而在出站这边，处理事件将会导致底层的套接字上发生一系列的动作。如下列出了 ChannelPipeline API 的出站
     * 操作。
     *
     * （1）bind：将 Channel 绑定到一个本地地址，这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * bind(ChannelHandlerContext, SocketAddress, ChannelPromise) 方法。
     *
     * （2）connect：将 Channel 连接到一个远程地址，这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * connect(ChannelHandlerContext, SocketAddress, ChannelPromise) 方法。
     *
     * （3）disconnect：将 Channel 断开连接。这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * disconnect(ChannelHandlerContext, ChannelPromise) 方法。
     *
     * （4）close：将 Channel 关闭。这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * close(ChannelHandlerContext, ChannelPromise) 方法。
     *
     * （5）deregister：将 Channel 从它先前所分配的 EventExecutor（即 EventLoop）中注销。这将调用 ChannelPipeline
     * 中的下一个 ChannelOutboundHandler 的 deregister(ChannelHandlerContext, ChannelPromise) 方法。
     *
     * （6）flush：冲刷 Channel 所有挂起的写入。这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * flush(ChannelHandlerContext) 方法。
     *
     * （7）write：将消息写入 Channel。这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * write(ChannelHandlerContext, Object msg, ChannelPromise) 方法。注意:这并不会将消息写入底层的
     * Socket，而只会将它放入队列中。要将它写入 Socket，需要调用 flush() 或者 writeAndFlush() 方法。
     *
     * （8）writeAndFlush：这是一个先调用 write() 方法再接着调用 flush() 方法的便利方法。
     *
     * （9）read：请求从 Channel 中读取更多的数据。这将调用 ChannelPipeline 中的下一个 ChannelOutboundHandler 的
     * read(ChannelHandlerContext) 方法。
     *
     *
     * 总结一下：
     * （1）ChannelPipeline 保存了与 Channel 相关联的 ChannelHandler；
     * （2）ChannelPipeline 可以根据需要，通过添加或者删除 ChannelHandler 来动态地修改；
     * （3）ChannelPipeline 有着丰富的 API 用以被调用，以响应入站和出站事件。
     */
    public static void main(String[] args) {

    }

}
