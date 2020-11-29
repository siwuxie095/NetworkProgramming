package com.siwuxie095.network.chapter11th.example5th;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 处理由行尾符分隔的帧
 *
 * @author Jiajing Li
 * @date 2020-11-29 15:21:58
 */
@SuppressWarnings("all")
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 该 LineBasedFrameDecoder 将提取的帧转发给下一个 ChannelInboundHandler
        pipeline.addLast(new LineBasedFrameDecoder(64 * 1024));
        // 添加 FrameHandler 以接收帧
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {

        /**
         * 传入了单个帧的内容
         */
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // Do something with the data extracted from the frame
        }

    }

}

