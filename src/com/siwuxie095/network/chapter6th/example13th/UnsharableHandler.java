package com.siwuxie095.network.chapter6th.example13th;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Sharable 的错误用法
 *
 * 使用注解 @Sharable 标注
 *
 * @author Jiajing Li
 * @date 2020-11-17 21:29:55
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class UnsharableHandler extends ChannelInboundHandlerAdapter {

    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 将 count 字段的值加 1
        count++;
        // 记录方法调用，并转发给下一个 ChannelHandler
        System.out.println("inboundBufferUpdated(...) called the "
                + count + " time");
        ctx.fireChannelRead(msg);
    }

}


