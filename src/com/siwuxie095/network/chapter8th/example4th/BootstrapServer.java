package com.siwuxie095.network.chapter8th.example4th;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 *
 * @author Jiajing Li
 * @date 2020-11-21 22:53:17
 */
@SuppressWarnings("all")
public class BootstrapServer {

    /**
     * 引导服务器
     */
    public void bootstrap() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 创建 Server Bootstrap
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置 EventLoopGroup，其提供了用于处理 Channel 事件的 EventLoop
        bootstrap.group(group)
                // 指定要使用的 Channel 实现
                .channel(NioServerSocketChannel.class)
                // 设置用于处理已被接受的子 Channel 的 I/O 及数据的 ChannelInboundHandler
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        // 通过配置好的 ServerBootstrap 的实例绑定该 Channel
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture)
                    throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Server bound");
                } else {
                    System.err.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

}

