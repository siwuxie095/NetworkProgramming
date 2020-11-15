package com.siwuxie095.network.chapter6th.example4th;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 使用 SimpleChannelInboundHandler
 *
 * 扩展了 SimpleChannelInboundHandler
 *
 * @author Jiajing Li
 * @date 2020-11-15 18:18:54
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        // 不需要任何显式的资源释放
        // No need to do anything special
    }
}
