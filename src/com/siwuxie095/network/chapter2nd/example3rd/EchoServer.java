package com.siwuxie095.network.chapter2nd.example3rd;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Jiajing Li
 * @date 2020-11-07 20:00:44
 */
@SuppressWarnings("all")
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    /**
     * 可以 Configuration 的 Program arguments 中设置参数作为 main 方法运行所需参数。
     *
     * 这里 main 方法运行所需参数实际上是用作端口，比如设置为 8000。
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        // 设置端口值（如果端口参数的格式不正确，则抛出一个 NumberFormatException ）
        int port = Integer.parseInt(args[0]);
        // 调用服务器的 start() 方法
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // (1) 创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // (2) 创建 ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // (3) 指定所使用的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // (4) 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // (5) 添加一个 EchoServerHandler 到于 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            /*
                             * EchoServerHandler 被标注为 @Shareable，所以可以总是使用同样的实例。
                             * （这里对于所有的客户端连接来说，都会使用同一个 EchoServerHandler，因为其被标注为 @Sharable。）
                             */
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // (6) 异步地绑定服务器；调用 sync() 方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listening for connections on " + f.channel().localAddress());
            // (7) 获取 Channel 的 CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            // (8) 关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}

