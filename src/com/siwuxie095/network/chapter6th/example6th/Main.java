package com.siwuxie095.network.chapter6th.example6th;

/**
 * @author Jiajing Li
 * @date 2020-11-15 22:16:17
 */
public class Main {

    /**
     * ChannelHandler 适配器
     *
     * 可以使用 ChannelInboundHandlerAdapter 和 ChannelOutboundHandlerAdapter 类作为自己的 ChannelHandler 的
     * 起始点。这两个适配器分别提供了 ChannelInboundHandler 和 ChannelOutboundHandler 的基本实现。通过扩展抽象类
     * ChannelHandlerAdapter，它们获得了它们共同的超接口 ChannelHandler 的方法。
     *
     * 相关类的层次结构如下：
     * （1）ChannelInboundHandler 接口继承自 ChannelHandler 接口；
     * （2）ChannelOutboundHandler 接口继承自 ChannelHandler 接口；
     * （3）ChannelHandlerAdapter 抽象类实现自 ChannelHandler 接口；
     * （4）ChannelInboundHandlerAdapter 类继承自 ChannelHandlerAdapter 抽象类，同时实现自 ChannelHandler 接口；
     * （5）ChannelOutboundHandlerAdapter 类继承自 ChannelHandlerAdapter 抽象类，同时实现自 ChannelHandler 接口。
     *
     * ChannelHandlerAdapter 还提供了实用方法 isSharable()。如果其对应的实现被标注为 Sharable（注解），那么这个方法
     * 将返回 true，表示它可以被添加到多个 ChannelPipeline 中。
     *
     * 在 ChannelInboundHandlerAdapter 和 ChannelOutboundHandlerAdapter 中所提供的方法体调用了其相关联的
     * ChannelHandlerContext 上的等效方法，从而将事件转发到了 ChannelPipeline 中的下一个 ChannelHandler 中。
     *
     * 你要想在自己的 ChannelHandler 中使用这些适配器类，只需要简单地扩展它们，并且重写那些你想要自定义的方法。
     */
    public static void main(String[] args) {

    }

}
