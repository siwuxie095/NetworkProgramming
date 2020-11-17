package com.siwuxie095.network.chapter6th.example14th;

/**
 * @author Jiajing Li
 * @date 2020-11-17 21:39:27
 */
public class Main {

    /**
     * 异常处理
     *
     * 异常处理是任何真实应用程序的重要组成部分，它也可以通过多种方式来实现。因此，Netty 提供了几种方式
     * 用于处理入站或者出站处理过程中所抛出的异常。
     *
     *
     *
     * 处理入站异常
     *
     * 如果在处理入站事件的过程中有异常被抛出，那么它将从它在 ChannelInboundHandler 里被触发的那一点
     * 开始流经 ChannelPipeline。要想处理这种类型的入站异常，你需要在你的 ChannelInboundHandler
     * 实现中重写下面的方法。
     *
     * public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
     *
     * 以 InboundExceptionHandler 为例，展示了一个简单的示例，其关闭了 Channel 并打印了异常的栈跟踪
     * 信息。
     *
     * 因为异常将会继续按照入站方向流动（就像所有的入站事件一样），所以实现了前面所示逻辑的
     * ChannelInboundHandler 通常位于 ChannelPipeline 的最后。这确保了所有的入站异常都总是会被处理，
     * 无论它们可能会发生在 ChannelPipeline 中的什么位置。
     *
     * 你应该如何响应异常，可能很大程度上取决于你的应用程序。你可能想要关闭 Channel（即 连接），也可能会
     * 尝试进行恢复。如果你不实现任何处理入站异常的逻辑（或者没有消费该异常）， 那么 Netty 将会记录该异常
     * 没有被处理的事实（即 Netty 将会通过 Warning 级别的日志记录该异常到达了 ChannelPipeline 的尾端，
     * 但没有被处理，并尝试释放该异常）。
     *
     * 总结一下：
     * （1）ChannelHandler.exceptionCaught() 的默认实现是简单地将当前异常转发给 ChannelPipeline
     * 中的下一个 ChannelHandler；
     * （2）如果异常到达了 ChannelPipeline 的尾端，它将会被记录为未被处理；
     * （3）要想定义自定义的处理逻辑，需要重写 exceptionCaught() 方法。然后需要决定是否需要将该异常传播
     * 出去。
     *
     *
     *
     * 处理出站异常
     *
     * 用于处理出站操作中的正常完成以及异常的选项，都基于以下的通知机制。
     * （1）每个出站操作都将返回一个 ChannelFuture。注册到 ChannelFuture 的 ChannelFutureListener
     * 将在操作完成时被通知该操作是成功了还是出错了。
     * （2）几乎所有的 ChannelOutboundHandler 上的方法都会传入一个 ChannelPromise 的实例。作为
     * ChannelFuture 的子类，ChannelPromise 也可以被分配用于异步通知的监听器。但是，ChannelPromise
     * 还具有提供立即通知的可写方法 ChannelPromise setSuccess() 和 ChannelPromise setFailure()。
     *
     * 添加 ChannelFutureListener 只需要调用 ChannelFuture 实例上的 addListener(ChannelFutureListener)
     * 方法，并且有两种不同的方式可以做到这一点。其中最常用的方式是，调用出站操作(如 write() 方法)所返回
     * 的 ChannelFuture 上的 addListener() 方法。
     *
     * 以 ChannelFutures 为例，使用了这种方式来添加 ChannelFutureListener，它将打印栈跟踪信息并且随
     * 后关闭 Channel。
     *
     * 第二种方式是将 ChannelFutureListener 添加到即将作为参数传递给 ChannelOutboundHandler 的方法
     * 的 ChannelPromise。以 OutboundExceptionHandler 为例，展示的代码和 ChannelFutures 中所展示
     * 的具有相同的效果。
     *
     * ChannelPromise 的可写方法：通过调用 ChannelPromise 上的 setSuccess() 和 setFailure() 方法，
     * 可以使一个操作的状态在 ChannelHandler 的方法返回给其调用者时便即刻被感知到。
     *
     * 为何选择一种方式而不是另一种呢（即 何时选择何种方式）？对于细致的异常处理，你可能会发现，在调用出站
     * 操作时添加 ChannelFutureListener 更合适，如 ChannelFutures 所示。而对于一般的异常处理，你可能
     * 会发现，OutboundExceptionHandler 所示的自定义的 ChannelOutboundHandler 实现的方式更加的简单。
     *
     * 如果你的 ChannelOutboundHandler 本身抛出了异常会发生什么呢在？这种情况下， Netty 本身会通知任何
     * 已经注册到对应 ChannelPromise 的监听器。
     */
    public static void main(String[] args) {

    }

}
