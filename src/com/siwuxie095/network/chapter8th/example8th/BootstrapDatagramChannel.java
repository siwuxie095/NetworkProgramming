package com.siwuxie095.network.chapter8th.example8th;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * 使用 Bootstrap 和 DatagramChannel
 *
 * @author Jiajing Li
 * @date 2020-11-22 17:51:12
 */
@SuppressWarnings("all")
public class BootstrapDatagramChannel {

    /**
     * 使用 Bootstrap 和 DatagramChannel
     */
    public void bootstrap() {
        // 创建一个 Bootstrap 的实例以创建和绑定新的数据报 Channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置 EventLoopGroup，其提供了用以处理 Channel 事件的 EventLoop
        bootstrap.group(new OioEventLoopGroup()).channel(
                // 指定 Channel 的实现
                OioDatagramChannel.class).handler(
                // 设置用以处理 Channel 的 I/O 以及数据的 ChannelInboundHandler
                new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
                        // Do something with the packet
                    }
                }
        );
        // 调用 bind() 方法，因为该协议是无连接的
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(0));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Channel bound");
                } else {
                    System.err.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

}

