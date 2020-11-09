package com.siwuxie095.network.chapter4th.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-09 07:31:51
 */
public class Main {

    /**
     * 案例研究：传输迁移
     *
     * 下面将从一个应用程序开始对传输的学习，这个应用程序只简单地接受连接，向客户端 写 "Hi!"，然后关闭连接。
     *
     *
     *
     * 不通过 Netty 使用 OIO 和 NIO
     *
     * 这里将介绍仅使用了 JDK API 的应用程序的阻塞（OIO）版本和异步（NIO）版本。
     *
     * 以 PlainOioServer 为例，展示了其阻塞版本的实现。
     *
     * 这段代码完全可以处理中等数量的并发客户端。但是随着应用程序变得流行起来，你会发现它并不能很好地伸缩到支撑成千上万
     * 的并发连入连接。于是决定改用异步网络编程，但是很快就发现异步 API 是完全不同的，以至于不得不重写你的应用程序。
     *
     * 以 PlainNioServer 为例，展示了其异步版本的实现。
     *
     * 虽然这段代码所做的事情与之前的版本完全相同，但是代码却截然不同。如果为了用于非阻塞 I/O 而重新实现这个简单的应用
     * 程序，都需要一次完全的重写的话，那么不难想象，移植真正复杂的应用程序需要付出什么样的努力。
     *
     * 鉴于此，下面来看看使用 Netty 实现该应用程序将会是什么样子吧。
     *
     *
     *
     * 通过 Netty 使用 OIO 和 NIO
     *
     * 以 NettyOioServer 为例，展示了其阻塞版本的实现。
     *
     * 以 NettyNioServer 为例，展示了其异步版本的实现。
     *
     * NettyOioServer 和 NettyNioServer 几乎一模一样，仅仅只有两处不同：
     * （1）NettyOioServer 使用的是 OioEventLoopGroup，NettyNioServer 使用的是 NioEventLoopGroup。
     * （2）NettyOioServer 使用的是 OioServerSocketChannel，NettyNioServer 使用的是 NioServerSocketChannel。
     *
     * 这就是从阻塞（OIO）传输切换到非阻塞（NIO）传输需要做的所有变更。
     *
     * 因为 Netty 为每种传输的实现都暴露了相同的 API，所以无论选用哪一种传输的实现，代码都仍然几乎不受影响。在所有的
     * 情况下，传输的实现都依赖于 Channel、ChannelPipeline 和 ChannelHandler 接口。
     */
    public static void main(String[] args) {

    }

}
