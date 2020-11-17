package com.siwuxie095.network.chapter6th.example13th;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 可共享的 ChannelHandler
 *
 * 使用注解 @Sharable 标注
 *
 * @author Jiajing Li
 * @date 2020-11-17 21:26:32
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class SharableHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("channel read message " + msg);
        // 记录方法调用，并转发给下一个 ChannelHandler
        ctx.fireChannelRead(msg);
    }

}
