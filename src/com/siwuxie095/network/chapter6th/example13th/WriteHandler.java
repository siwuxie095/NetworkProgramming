package com.siwuxie095.network.chapter6th.example13th;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 缓存到 ChannelHandlerContext 的引用
 *
 * @author Jiajing Li
 * @date 2020-11-17 21:21:56
 */
@SuppressWarnings("all")
public class WriteHandler extends ChannelHandlerAdapter {

    private ChannelHandlerContext ctx;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        // 存储到 ChannelHandlerContext 的引用以供稍后使用
        this.ctx = ctx;
    }

    public void send(String msg) {
        // 使用之前存储的到 ChannelHandlerContext 的引用来发送消息
        ctx.writeAndFlush(msg);
    }
}

