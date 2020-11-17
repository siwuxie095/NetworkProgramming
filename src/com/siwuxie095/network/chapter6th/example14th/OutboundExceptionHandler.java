package com.siwuxie095.network.chapter6th.example14th;

import io.netty.channel.*;

/**
 * 添加 ChannelFutureListener 到 ChannelPromise
 *
 * @author Jiajing Li
 * @date 2020-11-17 22:00:31
 */
@SuppressWarnings("all")
public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {

        promise.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }

        });

    }

}
