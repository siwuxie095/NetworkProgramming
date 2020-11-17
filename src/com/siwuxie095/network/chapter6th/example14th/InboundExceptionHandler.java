package com.siwuxie095.network.chapter6th.example14th;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 基本的入站异常处理
 *
 * @author Jiajing Li
 * @date 2020-11-17 21:42:26
 */
@SuppressWarnings("all")
public class InboundExceptionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
