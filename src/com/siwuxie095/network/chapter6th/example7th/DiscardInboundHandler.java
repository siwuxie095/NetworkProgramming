package com.siwuxie095.network.chapter6th.example7th;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 消费并释放入站消息
 *
 * 扩展了 ChannelInboundandlerAdapter
 *
 * @author Jiajing Li
 * @date 2020-11-16 07:52:04
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class DiscardInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 通过调用 ReferenceCountUtil.release() 方法释放资源
        ReferenceCountUtil.release(msg);
    }

}

