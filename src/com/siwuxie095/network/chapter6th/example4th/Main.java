package com.siwuxie095.network.chapter6th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-15 17:58:55
 */
public class Main {

    /**
     * ChannelInboundHandler 接口
     *
     * 如下列出了 ChannelInboundHandler 接口的生命周期方法。这些方法将会在数据被接收时或者与其对应的 Channel 状态
     * 发生改变时被调用。也就是说，这些方法和 Channel 的生命周期密切相关。
     *
     * （1）channelRegistered：当 Channel 已经注册到它的 EventLoop 并且能够处理 I/O 时被调用。
     * （2）channelUnregistered：当 Channel 从它的 EventLoop 注销并且无法处理任何 I/O 时被调用。
     * （3）channelActive：当 Channel 处于活动（活跃）状态时被调用；Channel 已经连接/绑定并且已经就绪。
     * （4）channelInactive：当 Channel 离开活动状态并且不再连接它的远程节点时被调用。
     * （5）channelReadComplete：当 Channel 上的一个读操作完成时被调用（PS：当所有可读的字节都已经从 Channel 中
     * 读取之后，将会调用该回调方法。所以，可能在channelReadComplete() 被调用之前看到多次调用 channelRead()）。
     * （6）channelRead：当从 Channel 读取数据时被调用。
     * （7）channelWritabilityChanged：当Channel的可写状态发生改变时被调用。用户可以确保写操作不会完成得太快（以
     * 避免发生 OutOfMemoryError）或者可以在 Channel 变为再次可写时恢复写入。可以通过调用 Channel 的 isWritable()
     * 方法来检测 Channel 的可写性。与可写性相关的阈值可以通过 Channel.config().setWriteHighWaterMark() 和
     * Channel.config().setWriteLowWaterMark() 方法来设置。
     * （8）userEventTriggered：当 ChannelInboundHandler.fireUserEventTriggered() 方法被调用时被调用，因为
     * 一个 POJO 被传经（传递经过）了 ChannelPipeline。
     *
     * 当某个 ChannelInboundHandler 的实现重写 channelRead() 方法时，它将负责显式地释放与池化的 ByteBuf 实例相
     * 关的内存。Netty 为此提供了一个实用方法 ReferenceCountUtil.release()。
     *
     * 以 DiscardHandler 为例，展示了 ReferenceCountUtil.release() 的用法。
     *
     * Netty 将使用 WARN 级别的日志消息记录未释放的资源，使得可以非常简单地在代码中发现违规的实例。但是以这种方式管理
     * 资源可能很繁琐。一个更加简单的方式是使用 SimpleChannelInboundHandler。
     *
     * 以 SimpleDiscardHandler 为例，它是 DiscardHandler 的一个变体，说明了这一点。
     *
     * 由于 SimpleChannelInboundHandler 会自动释放资源，所以不应该存储指向任何消息的引用供将来使用，因为这些引用都
     * 将会失效。
     */
    public static void main(String[] args) {

    }

}
