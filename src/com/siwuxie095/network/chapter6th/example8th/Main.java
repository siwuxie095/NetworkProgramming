package com.siwuxie095.network.chapter6th.example8th;

/**
 * @author Jiajing Li
 * @date 2020-11-16 08:05:20
 */
public class Main {

    /**
     * ChannelPipeline 接口
     *
     * 如果你认为 ChannelPipeline 是一个拦截流经 Channel 的入站和出站事件的 ChannelHandler 实例链，那么就很容易
     * 看出这些 ChannelHandler 之间的交互是如何组成一个应用程序数据和事件处理逻辑的核心的。
     *
     * 每一个新创建的 Channel 都将会被分配一个新的 ChannelPipeline。这项关联是永久性的。Channel 既不能附加另外一
     * 个 ChannelPipeline，也不能分离其当前的。在 Netty 组件的生命周期中，这是一项固定的操作，不需要开发人员的任何
     * 干预。
     *
     * 根据事件的起源，事件将会被 ChannelInboundHandler 或者 ChannelOutboundHandler 处理。随后，通过调用
     * ChannelHandlerContext 实现，它将被转发给同一超类型的下一个 ChannelHandler。
     *
     * 关于 ChannelHandlerContext：ChannelHandlerContext 使得 ChannelHandler 能够和它的 ChannelPipeline
     * 以及其他的 ChannelHandler 交互。ChannelHandler 可以通知其所属的 ChannelPipeline 中的下一个 ChannelHandler，
     * 甚至可以动态修改它所属的 ChannelPipeline（这里指修改 ChannelPipeline 中的 ChannelHandler 的编排）。
     *
     * ChannelPipeline 主要由一系列的 ChannelHandler 所组成，ChannelPipeline 还提供了通过 ChannelPipeline
     * 本身传播事件的方法。如果一个入站事件被触发，它将被从 ChannelPipeline 的头部开始一直被传播到 ChannelPipeline
     * 的尾端。
     *
     * 关于 ChannelPipeline 相对论：你可能会说，从事件途经 ChannelPipeline 的角度来看，ChannelPipeline 的头部
     * 和尾端取决于该事件是入站的还是出站的。然而 Netty 总是将 ChannelPipeline 的入站口作为头部，将出站口作为尾端。
     *
     * 当你完成了通过调用 ChannelPipeline.add*() 方法将入站处理器（ChannelInboundHandler）和出站处理器
     * （ChannelOutboundHandler）混合添加到 ChannelPipeline 之后，每一个 ChannelHandler 从头部到尾端的顺序位
     * 置就已经定义好了。如果加入的顺序是这样：
     *
     * 入站处理器1 -> 入站处理器2 -> 出站处理器3 -> 入站处理器4 -> 出站处理器5
     *
     * 那么第一个被入站事件看到的 ChannelHandler 将是 1（入站口，头部），而第一个被出站事件看到的 ChannelHandler
     * 将是 5（出站口，尾端）。
     *
     * 在 ChannelPipeline 传播事件时，它会测试 ChannelPipeline 中的下一个 ChannelHandler 的类型是否和事件的运
     * 动方向相匹配。如果不匹配，ChannelPipeline 将跳过该 ChannelHandler 并前进到下一个，直到它找到和该事件所期望
     * 的方向相匹配的为止。
     *
     * PS：当然，ChannelHandler 也可以同时实现 ChannelInboundHandler 接口和 ChannelOutboundHandler 接口。
     */
    public static void main(String[] args) {

    }

}
