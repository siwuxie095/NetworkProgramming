package com.siwuxie095.network.chapter11th.example6th;

import io.netty.channel.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedStream;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * 使用 ChunkedStream 传输文件内容
 *
 * @author Jiajing Li
 * @date 2020-11-29 16:41:10
 */
@SuppressWarnings("all")
public class ChunkedWriteHandlerInitializer extends ChannelInitializer<Channel> {

    private final File file;
    private final SslContext sslCtx;

    public ChunkedWriteHandlerInitializer(File file, SslContext sslCtx) {
        this.file = file;
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 将 SslHandler 添加到 ChannelPipeline 中
        pipeline.addLast(new SslHandler(sslCtx.newEngine(ch.alloc())));
        // 添加 ChunkedWriteHandler 以处理作为 ChunkedInput 传入的数据
        pipeline.addLast(new ChunkedWriteHandler());
        // 一旦连接建立，WriteStreamHandler 就开始写文件数据
        pipeline.addLast(new WriteStreamHandler());
    }

    public final class WriteStreamHandler extends ChannelInboundHandlerAdapter {

        /**
         * 当连接建立时，channelActive() 方法将使用 ChunkedInput 写文件数据
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            ctx.writeAndFlush(
                    new ChunkedStream(new FileInputStream(file)));
        }

    }

}

