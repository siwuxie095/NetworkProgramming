package com.siwuxie095.network.chapter6th.example7th;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * 丢弃并释放出站消息
 *
 * 扩展了 ChannelOutboundHandlerAdapter
 *
 * @author Jiajing Li
 * @date 2020-11-16 07:53:26
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        // 通过使用 ReferenceCountUtil.release(...) 方法释放资源
        ReferenceCountUtil.release(msg);
        // 通知 ChannelPromise 数据已经被处理了
        promise.setSuccess();
    }

}
