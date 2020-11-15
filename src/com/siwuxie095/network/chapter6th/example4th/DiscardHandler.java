package com.siwuxie095.network.chapter6th.example4th;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 释放消息资源
 *
 * 扩展了 ChannelInboundHandlerAdapter
 *
 * @author Jiajing Li
 * @date 2020-11-15 18:14:28
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class DiscardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 丢弃已接收的消息
        ReferenceCountUtil.release(msg);
    }

}
