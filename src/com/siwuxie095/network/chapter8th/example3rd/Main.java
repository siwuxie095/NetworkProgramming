package com.siwuxie095.network.chapter8th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-21 21:51:43
 */
public class Main {

    /**
     * 引导客户端和无连接协议
     *
     * Bootstrap 类被用于客户端或者使用了无连接协议的应用程序中。如下是该类的一个概览，其中许多方法都继承
     * 自 AbstractBootstrap 类。
     *
     * （1）Bootstrap group(EventLoopGroup)：
     * 设置用于处理 Channel 所有事件的 EventLoopGroup。
     *
     * （2）Bootstrap channel(Class<? extends C>)、Bootstrap channelFactory(ChannelFactory<? extends C>)：
     * channel() 方法指定了 Channel 的实现类。如果该实现类没提供默认的构造函数（这里指默认的无参构造函数，
     * 因为内部使用了反射来实现Channel的创建），可以通过调用 channelFactory() 方法来指定一个工厂类，它将
     * 会被 bind() 方法调用。
     *
     * （3）Bootstrap localAddress(SocketAddress)：
     * 指定 Channel 应该绑定到的本地地址。如果没有指定，则将由操作系统创建一个随机的地址。或者，也可以通过
     * bind() 或者 connect() 方法指定 localAddress。
     *
     * （4）<T> Bootstrap option(ChannelOption<T> option, T value)：
     * 设置 ChannelOption，其将被应用到每个新创建的 Channel 的 ChannelConfig。这些选项将会通过 bind()
     * 或者 connect() 方法设置到 Channel，不管哪个先被调用。这个方法在 Channel 已经被创建后再调用将不会
     * 有任何的效果。支持的 ChannelOption 取决于使用的 Channel 类型。
     *
     * （5）<T> Bootstrap attr(Attribute<T> key, T value)：
     * 指定新创建的 Channel 的属性值。这些属性值是通过 bind() 或者 connect() 方法设置到 Channel 的，具
     * 体取决于谁最先被调用。这个方法在 Channel 被创建后将不会有任何的效果。
     *
     * （6）Bootstrap handler(ChannelHandler)：
     * 设置将被添加到 ChannelPipeline 以接收事件通知的 ChannelHandler。
     *
     * （7）Bootstrap clone()：
     * 创建一个当前 Bootstrap 的克隆，其具有和原始的 Bootstrap 相同的设置信息。
     *
     * （8）Bootstrap remoteAddress(SocketAddress)：
     * 设置远程地址。或者，也可以通过 connect() 方法来指定它。
     *
     * （9）ChannelFuture connect()：
     * 连接到远程节点并返回一个 ChannelFuture，其将会在连接操作完成后接收到通知。
     *
     * （10）ChannelFuture bind()：
     * 绑定 Channel 并返回一个 ChannelFuture，其将会在绑定操作完成后接收到通知，在那之后必须调用 Channel
     * 的 connect() 方法来建立连接。
     *
     * 下面将一步一步地讲解客户端的引导过程。也将讨论在选择可用的组件实现时保持兼容性的问题。
     *
     *
     *
     * 1、引导客户端示例
     *
     * Bootstrap 类负责为客户端和使用无连接协议的应用程序创建 Channel，如下：
     * （1）Bootstrap 类将会在 bind() 方法被调用后创建一个新的 Channel，在这之后将会调用 connect() 方法
     * 以建立连接；
     * （2）在 connect() 方法被调用后，Bootstrap 类将会创建一个新的 Channel。
     *
     * 以 BootstrapClient 为例，引导了一个使用 NIO TCP 传输的客户端。
     *
     * 这个示例使用了流式语法，这些方法（除了 connect() 方法以外）将通过每次方法调用所返回的对 Bootstrap
     * 实例的引用链接在一起。
     *
     *
     *
     * 2、Channel 和 EventLoopGroup 的兼容性
     *
     * io.netty.channel 包中有如下所示的兼容代码：
     * channel:
     *          nio：
     *              NioEventLoopGroup
     *          oio：
     *              OioEventLoopGroup
     *          socket：
     *                  nio：
     *                      NioDatagramChannel
     *                      NioServerSocketChannel
     *                      NioSocketChannel
     *                  oio：
     *                      OioDatagramChannel
     *                      OioServerSocketChannel
     *                      OioSocketChannel
     *
     * 可以从包名以及与其相对应的类名的前缀看到，对于 NIO 以及 OIO 传输两者来说，都有相关的 EventLoopGroup
     * 和 Channel 实现。
     *
     * 必须保持这种兼容性，而不能混用具有不同前缀的组件，如 NioEventLoopGroup 和 OioSocketChannel。
     *
     * 以 InvalidBootstrapClient 为例，展示了试图这样做的一个例子。
     *
     * 这段代码将会导致 IllegalStateException，因为它混用了不兼容的传输。如下：
     *
     * Exception in thread "main" java.lang.IllegalStateException: incompatible event loop type: io.netty.channel.nio.NioEventLoop
     * 	at io.netty.channel.AbstractChannel$AbstractUnsafe.register(AbstractChannel.java:461)
     * 	at io.netty.channel.SingleThreadEventLoop.register(SingleThreadEventLoop.java:87)
     * 	at io.netty.channel.SingleThreadEventLoop.register(SingleThreadEventLoop.java:81)
     * 	at io.netty.channel.MultithreadEventLoopGroup.register(MultithreadEventLoopGroup.java:86)
     * 	at io.netty.bootstrap.AbstractBootstrap.initAndRegister(AbstractBootstrap.java:323)
     * 	at io.netty.bootstrap.Bootstrap.doResolveAndConnect(Bootstrap.java:155)
     * 	at io.netty.bootstrap.Bootstrap.connect(Bootstrap.java:139)
     * 	at com.siwuxie095.network.chapter8th.example3rd.InvalidBootstrapClient.bootstrap(InvalidBootstrapClient.java:47)
     * 	at com.siwuxie095.network.chapter8th.example3rd.InvalidBootstrapClient.main(InvalidBootstrapClient.java:25)
     *
     *
     * 关于 IllegalStateException 的更多讨论：
     * 在引导的过程中，在调用 bind() 或者 connect() 方法之前，必须调用以下方法来设置所需的组件：
     * （1）group()
     * （2）channel() 或者 channelFactory()
     * （3）handler()
     *
     * 如果不这样做，则将会导致 IllegalStateException。对 handler()方法的调用尤其重要，因为它需要配置
     * 好 ChannelPipeline。
     */
    public static void main(String[] args) {

    }

}
