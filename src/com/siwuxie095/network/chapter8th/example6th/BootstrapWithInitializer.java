package com.siwuxie095.network.chapter8th.example6th;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.net.InetSocketAddress;

/**
 * 引导和使用 ChannelInitializer
 *
 * @author Jiajing Li
 * @date 2020-11-22 17:10:11
 */
@SuppressWarnings("all")
public class BootstrapWithInitializer {

    /**
     * 引导和使用 ChannelInitializer
     */
    public void bootstrap() throws InterruptedException {
        // 创建 ServerBootstrap 以创建和绑定新的 Channel
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置 EventLoopGroup，其将提供用以处理 Channel 事件的 EventLoop
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 指定 Channel 的实现
                .channel(NioServerSocketChannel.class)
                // 注册一个 ChannelInitializerImpl 的实例来设置 ChannelPipeline
                .childHandler(new ChannelInitializerImpl());
        // 绑定到地址
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.sync();
    }

    /**
     * 用以设置 ChannelPipeline 的自定义 ChannelInitializerImpl 实现
     *
     * 在大部分的场景下，如果你不需要使用只存在于 SocketChannel 上的方法，使用 ChannelInitializer<Channel>
     * 就可以了，否则你可以使用 ChannelInitializer<SocketChannel>，其中 SocketChannel 扩展了 Channel
     */
    final class ChannelInitializerImpl extends ChannelInitializer<Channel> {

        /**
         * 将所需的 ChannelHandler 添加到 ChannelPipeline
         */
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }

    }

}

