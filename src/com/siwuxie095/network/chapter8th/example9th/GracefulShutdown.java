package com.siwuxie095.network.chapter8th.example9th;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

/**
 * 优雅关闭
 *
 * @author Jiajing Li
 * @date 2020-11-22 17:59:52
 */
@SuppressWarnings("all")
public class GracefulShutdown {

    public static void main(String[] args) {
        GracefulShutdown client = new GracefulShutdown();
        client.bootstrap();
    }

    /**
     * 优雅关闭
     */
    public void bootstrap() {
        // 创建处理 I/O 的 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个 Bootstrap 类的实例并配置它
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                // ...
                .handler(
                        new SimpleChannelInboundHandler<ByteBuf>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                System.out.println("Received data");
                            }
                        }
                );
        bootstrap.connect(new InetSocketAddress("www.manning.com", 80)).syncUninterruptibly();
        // ...
        // shutdownGracefully() 方法将释放所有的资源，并且关闭所有的当前正在使用中的 Channel
        Future<?> future = group.shutdownGracefully();
        // block until the group has shutdown
        future.syncUninterruptibly();
    }
}

