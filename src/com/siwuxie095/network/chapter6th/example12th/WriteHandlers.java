package com.siwuxie095.network.chapter6th.example12th;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * @author Jiajing Li
 * @date 2020-11-17 20:41:52
 */
@SuppressWarnings("all")
public class WriteHandlers {

    private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE = DummyChannelHandlerContext.DUMMY_INSTANCE;
    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DummyChannelPipeline.DUMMY_INSTANCE;

    /**
     * 从 ChannelHandlerContext 访问 Channel
     */
    public static void writeViaChannel() {
        // get reference from somewhere
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        // 获取到与 ChannelHandlerContext 相关联的 Channel 的引用
        Channel channel = ctx.channel();
        // 通过 Channel 写入缓冲区
        channel.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }

    /**
     * 通过 ChannelHandlerContext 访问 ChannelPipeline
     */
    public static void writeViaChannelPipeline() {
        //get reference from somewhere
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        // 获取到与 ChannelHandlerContext 相关联的 ChannelPipeline 的引用
        ChannelPipeline pipeline = ctx.pipeline();
        // 通过 ChannelPipeline 写入缓冲区
        pipeline.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }

    /**
     * 调用 ChannelHandlerContext 的 write() 方法
     */
    public static void writeViaChannelHandlerContext() {
        // get reference from somewhere
        // 获取到 ChannelHandlerContext 的引用
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        // write() 方法将把缓冲区数据发送到下一个 ChannelHandler
        ctx.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }

}

