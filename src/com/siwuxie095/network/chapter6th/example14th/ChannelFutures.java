package com.siwuxie095.network.chapter6th.example14th;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 添加 ChannelFutureListener 到 ChannelFuture
 *
 * @author Jiajing Li
 * @date 2020-11-17 21:52:54
 */
@SuppressWarnings("all")
public class ChannelFutures {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    private static final ByteBuf SOME_MSG_FROM_SOMEWHERE = Unpooled.buffer(1024);

    /**
     * 添加 ChannelFutureListener 到 ChannelFuture
     */
    public static void addingChannelFutureListener() {
        // get reference to pipeline;
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        // get reference to pipeline;
        ByteBuf someMessage = SOME_MSG_FROM_SOMEWHERE;
        // ...
        ChannelFuture future = channel.write(someMessage);
        future.addListener(new ChannelFutureListener() {

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

