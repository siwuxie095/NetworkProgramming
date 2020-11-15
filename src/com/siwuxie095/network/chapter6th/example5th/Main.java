package com.siwuxie095.network.chapter6th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-15 20:06:41
 */
public class Main {

    /**
     * ChannelOutboundHandler 接口
     *
     * 出站操作和数据将由 ChannelOutboundHandler 处理。它的方法将被 Channel、ChannelPipeline 以及
     * ChannelHandlerContext 调用。
     *
     * ChannelOutboundHandler 的一个强大的功能是可以按需推迟操作或者事件，这使得可以通过一些复杂的方法
     * 来处理请求。例如，如果到远程节点的写入被暂停了，那么你可以推迟冲刷操作并在稍后继续。
     *
     * 如下列出了所有由 ChannelOutboundHandler 本身所定义的方法（忽略了那些从 ChannelHandler 继承的
     * 方法）。
     *
     * （1）bind(ChannelHandlerContext, SocketAddress, ChannelPromise)：
     * 当请求将 Channel 绑定到本地地址时被调用。
     *
     * （2）connect(ChannelHandlerContext, SocketAddress,SocketAddress,ChannelPromise)：
     * 当请求将 Channel 连接到远程节点时被调用。
     *
     * （3）disconnect(ChannelHandlerContext, ChannelPromise)：
     * 当请求将 Channel 从远程节点断开时被调用。
     *
     * （4）close(ChannelHandlerContext, ChannelPromise)：
     * 当请求关闭 Channel 时被调用。
     *
     * （5）deregister(ChannelHandlerContext, ChannelPromise)：
     * 当请求将 Channel 从它的 EventLoop 注销时被调用。
     *
     * （6）read(ChannelHandlerContext)：
     * 当请求从 Channel 读取更多的数据时被调用。
     *
     * （7）flush(ChannelHandlerContext)：
     * 当请求通过 Channel 将入队数据冲刷到远程节点时被调用。
     *
     * （8）write(ChannelHandlerContext,Object, ChannelPromise)：
     * 当请求通过 Channel 将数据写到远程节点时被调用。
     *
     *
     * 关于 ChannelPromise 与 ChannelFuture：
     *
     * ChannelOutboundHandler 中的大部分方法都需要一个 ChannelPromise 参数，以便在操作完成时得到通知。
     * ChannelPromise 是 ChannelFuture 的一个子类，其定义了一些可写的方法，如 setSuccess() 和
     * setFailure()，从而使 ChannelFuture 不可变。
     *
     * PS：这里借鉴的是 Scala 的 Promise 和 Future 的设计，当一个 Promise 被完成之后，其对应的 Future
     * 的值便不能再进行任何修改了。
     */
    public static void main(String[] args) {

    }

}
