package com.siwuxie095.network.chapter6th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-15 17:50:48
 */
public class Main {

    /**
     * ChannelHandler 的生命周期
     *
     * 如下列出了 ChannelHandler 接口定义的生命周期操作，在 ChannelHandler 被添加到 ChannelPipeline 中或者
     * 被从 ChannelPipeline 中移除时会调用这些操作。这些方法中的每一个都接受一个 ChannelHandlerContext 参数。
     *
     * （1）handlerAdded：当把 ChannelHandler 添加到 ChannelPipeline 中时被调用。
     * （2）handlerRemoved：当从 ChannelPipeline 中移除 ChannelHandler 时被调用。
     * （3）exceptionCaught：当处理过程中在 ChannelPipeline 中有错误产生时被调用。
     *
     *
     * Netty 定义了下面两个重要的 ChannelHandler 子接口：
     *
     * （1）ChannelInboundHandler：处理入站数据以及各种状态变化。
     * （2）ChannelOutboundHandler：处理出站数据并且允许拦截所有的操作。
     */
    public static void main(String[] args) {

    }

}
