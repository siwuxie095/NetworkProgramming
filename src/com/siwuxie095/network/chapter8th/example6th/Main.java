package com.siwuxie095.network.chapter8th.example6th;

/**
 * @author Jiajing Li
 * @date 2020-11-22 17:00:25
 */
public class Main {

    /**
     * 在引导过程中添加多个 ChannelHandler
     *
     * 在引导的过程中调用 handler() 或者 childHandler() 方法来添加单个的 ChannelHandler，这对于简单的应用程序来说，
     * 可能已经足够了，但是它不能满足更加复杂的需求。例如，一个必须要支持多种协议的应用程序将会有很多的 ChannelHandler，
     * 而不会是一个庞大而又笨重的类。
     *
     * 正如经常所看到的一样，可以根据需要，通过在 ChannelPipeline 中将它们链接在一起来部署尽可能多的 ChannelHandler。
     * 但是，如果在引导的过程中只能设置一个 ChannelHandler，那么应该怎么做到这一点呢？
     *
     * 正是针对于这个用例，Netty 提供了一个特殊的 ChannelInboundHandlerAdapter 子类，即 ChannelInitializer：
     *
     * public abstract class ChannelInitializer<C extends Channel> extends ChannelInboundHandlerAdapter
     *
     * 它定义了下面的方法：
     *
     *     protected abstract void initChannel(C ch) throws Exception;
     *
     * 这个方法提供了一种将多个 ChannelHandler 添加到一个 ChannelPipeline 中的简便方法。你只需要简单地向 Bootstrap
     * 或 ServerBootstrap 的实例提供你的 ChannelInitializer 实现即可，并且一旦 Channel 被注册到了它的 EventLoop
     * 之后，就会调用你的 initChannel() 版本。在该方法返回之后，ChannelInitializer 的实例将会从 ChannelPipeline
     * 中移除它自己。
     *
     * 以 BootstrapWithInitializer 为例，其中定义了 ChannelInitializerImpl 类，并通过 ServerBootstrap 的
     * childHandler() 方法注册它（注册到 ServerChannel 的子 Channel 的 ChannelPipeline）。可以看到，这个看似复杂
     * 的操作实际上是相当简单直接的。
     *
     * 如果应用程序使用了多个 ChannelHandler，请定义自己的 ChannelInitializer 实现把它们安装到 ChannelPipeline 中。
     */
    public static void main(String[] args) {

    }

}
