package com.siwuxie095.network.chapter6th.example11th;

/**
 * @author Jiajing Li
 * @date 2020-11-17 08:08:23
 */
public class Main {

    /**
     * ChannelHandlerContext 接口
     *
     * ChannelHandlerContext 代表了 ChannelHandler 和 ChannelPipeline 之间的关联，每当有 ChannelHandler添加
     * 到 ChannelPipeline 中时，都会创建 ChannelHandlerContext。ChannelHandlerContext 的主要功能是管理它所关
     * 联的 ChannelHandler 和在同一个 ChannelPipeline 中的其他 ChannelHandler 之间的交互。
     *
     * ChannelHandlerContext 有很多的方法，其中一些方法也存在于 Channel 和 ChannelPipeline 本身上，但是有一点重
     * 要的不同。如果调用 Channel 或者 ChannelPipeline 上的这些方法，它们将沿着整个 ChannelPipeline 进行传播。而
     * 调用位于 ChannelHandlerContext 上的相同方法，则将从当前所关联的 ChannelHandler 开始，并且只会传播给位于该
     * ChannelPipeline 中的下一个能够处理该事件的 ChannelHandler。
     *
     * 如下是对 ChannelHandlerContext API 的总结：
     * （1）alloc：
     * 返回和这个实例相关联的 Channel 所配置的 ByteBufAllocator；
     *
     * （2）bind：
     * 绑定到给定的 SocketAddress，并返回 ChannelFuture；
     *
     * （3）channel：
     * 返回绑定到这个实例的 Channel；
     *
     * （4）close：
     * 关闭 Channel，并返回 ChannelFuture；
     *
     * （5）connect：
     * 连接给定的 SocketAddress，并返回 ChannelFuture；
     *
     * （6）deregister：
     * 从之前分配的 EventExecutor 注销，并返回 ChannelFuture；
     *
     * （7）disconnect：
     * 从远程节点断开，并返回 ChannelFuture；
     *
     * （8）executor：
     * 返回调度事件的 EventExecutor；
     *
     * （9）fireChannelActive：
     * 触发对下一个 ChannelInboundHandler 上的 channelActive() 方法（已连接）的调用；
     *
     * （10）fireChannelInactive：
     * 触发对下一个 ChannelInboundHandler 上的 channelInactive() 方法（已关闭）的调用；
     *
     * （11）fireChannelRead：
     * 触发对下一个 ChannelInboundHandler 上的 channelRead() 方法（已接收的消息）的调用；
     *
     * （12）fireChannelReadComplete：
     * 触发对下一个 ChannelInboundHandler 上的 channelReadComplete() 方法的调用；
     *
     * （13）fireChannelRegistered：
     * 触发对下一个 ChannelInboundHandler 上的 fireChannelRegistered() 方法的调用；
     *
     * （14）fireChannelUnregistered：
     * 触发对下一个 ChannelInboundHandler 上的 fireChannelUnregistered() 方法的调用；
     *
     * （15）fireChannelWritabilityChanged：
     * 触发对下一个 ChannelInboundHandler 上的 fireChannelWritabilityChanged() 方法的调用；
     *
     * （16）fireExceptionCaught：
     * 触发对下一个 ChannelInboundHandler 上的 fireExceptionCaught(Throwable) 方法的调用；
     *
     * （17）fireUserEventTriggered：
     * 触发对下一个 ChannelInboundHandler 上的 fireUserEventTriggered(Object evt) 方法的调用；
     *
     * （18）handler：
     * 返回绑定到这个实例的 ChannelHandler；
     *
     * （19）isRemoved：
     * 如果所关联的 ChannelHandler 已经被从 ChannelPipeline 中移除则返回 true；
     *
     * （20）name：
     * 返回这个实例的唯一名称；
     *
     * （21）pipeline：
     * 返回这个实例所关联的 ChannelPipeline；
     *
     * （22）read：
     * 将数据从 Channel 读取到第一个入站缓冲区；如果读取成功则触发一个 channelRead 事件，并（在最后一个消息被读取
     * 完成后）通知 ChannelInboundHandler 的 channelReadComplete(ChannelHandlerContext) 方法（通过配合
     * ChannelConfig.setAutoRead(boolean autoRead) 方法，可以实现反应式系统的特性之一：回压 back-pressure）。
     */
    public static void main(String[] args) {

    }

}
