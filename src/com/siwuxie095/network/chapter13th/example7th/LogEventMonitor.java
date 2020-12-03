package com.siwuxie095.network.chapter13th.example7th;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * @author Jiajing Li
 * @date 2020-12-03 08:23:12
 */
@SuppressWarnings("all")
public class LogEventMonitor {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public LogEventMonitor(InetSocketAddress address) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        // 引导该 NioDatagramChannel
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                // 设置套接字选项 SO_BROADCAST
                .option(ChannelOption.SO_BROADCAST, true)
                .handler( new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel)
                            throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        // 将 LogEventDecoder 和 LogEventHandler 添加到 ChannelPipeline 中
                        pipeline.addLast(new LogEventDecoder());
                        pipeline.addLast(new LogEventHandler());
                    }
                } )
                .localAddress(address);
    }

    public Channel bind() {
        // 绑定 Channel。注意，DatagramChannel 是无连接的
        return bootstrap.bind().syncUninterruptibly().channel();
    }

    public void stop() {
        group.shutdownGracefully();
    }

    /**
     * 可以 Configuration 的 Program arguments 中设置参数作为 main 方法运行所需参数。
     *
     * 这里 main 方法运行所需参数实际上是用作端口，比如设置为 8000。
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Usage: LogEventMonitor <port>");
        }
        // 构造一个新的 LogEventMonitor
        LogEventMonitor monitor = new LogEventMonitor(
                new InetSocketAddress(Integer.parseInt(args[0])));
        try {
            Channel channel = monitor.bind();
            System.out.println("LogEventMonitor running");
            channel.closeFuture().sync();
        } finally {
            monitor.stop();
        }
    }
}

