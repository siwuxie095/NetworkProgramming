package com.siwuxie095.network.chapter8th.example5th;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 *
 * @author Jiajing Li
 * @date 2020-11-22 16:47:18
 */
@SuppressWarnings("all")
public class BootstrapSharingEventLoopGroup {

    /**
     * 引导服务器
     */
    public void bootstrap() {
        // 创建 ServerBootstrap 以创建 ServerSocketChannel，并绑定它
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置 EventLoopGroup，其将提供用以处理 Channel 事件的 EventLoop
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 指定要使用的 Channel 实现
                .channel(NioServerSocketChannel.class)
                // 设置用于处理已被接受的子 Channel 的 I/O 和数据的 ChannelInboundHandler
                .childHandler(
                        new SimpleChannelInboundHandler<ByteBuf>() {
                            ChannelFuture connectFuture;
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                // 创建一个 Bootstrap 类的实例以连接到远程主机
                                Bootstrap bootstrap = new Bootstrap();
                                // 指定 Channel 的实现
                                bootstrap.channel(NioSocketChannel.class).handler(
                                        // 为入站 I/O 设置 ChannelInboundHandler
                                        new SimpleChannelInboundHandler<ByteBuf>() {
                                            @Override
                                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
                                                System.out.println("Received data");
                                            }
                                        });
                                // 使用与分配给已被接受的子 Channel 相同的 EventLoop
                                bootstrap.group(ctx.channel().eventLoop());
                                // 连接到远程节点
                                connectFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
                            }

                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                if (connectFuture.isDone()) {
                                    // 当连接完成时，执行一些数据操作（如代理）
                                    // do something with the data
                                }
                            }
                        });
        // 通过配置好的 ServerBootstrap 绑定该 ServerSocketChannel
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
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

