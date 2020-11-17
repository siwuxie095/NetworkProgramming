package com.siwuxie095.network.chapter6th.example13th;

/**
 * @author Jiajing Li
 * @date 2020-11-17 21:18:25
 */
@SuppressWarnings("all")
public class Main {

    /**
     * ChannelHandler 和 ChannelHandlerContext 的高级用法
     *
     * 已经知道，可以通过调用 ChannelHandlerContext 上的 pipeline() 方法来获得被封闭的 ChannelPipeline 的引用。
     * 这使得运行时得以操作 ChannelPipeline 的 ChannelHandler，可以利用这一点来实现一些复杂的设计。例如，可以通过
     * 将 ChannelHandler 添加到 ChannelPipeline 中来实现动态的协议切换。
     *
     * 另一种高级的用法是缓存到 ChannelHandlerContext 的引用以供稍后使用，这可能会发生在任何的 ChannelHandler 方
     * 法之外，甚至来自于不同的线程。以 WriteHandler 为例，展示了用这种模式来触发事件。
     *
     * 因为一个 ChannelHandler 可以从属于多个 ChannelPipeline，所以它也可以绑定到多个 ChannelHandlerContext
     * 实例。对于这种用法指在多个 ChannelPipeline 中共享同一个 ChannelHandler，对应的 ChannelHandler 必须要使用
     * @Sharable 注解标注，否则，试图将它添加到多个 ChannelPipeline 时将会触发异常。显而易见，为了安全地被用于多个
     * 并发的 Channel（即 连接），这样的 ChannelHandler 必须是线程安全的。
     *
     * 以 SharableHandler 为例，展示了这种模式的一个正确实现。
     *
     * 前例的 ChannelHandler 实现符合所有的将其加入到多个 ChannelPipeline 的需求，即 它使用了注解 @Sharable 标注，
     * 并且也不持有任何的状态。但以 UnsharableHandler 为例，其中的实现将会导致问题。
     *
     * 这段代码的问题在于它拥有状态，即用于跟踪方法调用次数的实例变量 count。将这个类的一个实例添加到 ChannelPipeline
     * 将极有可能在它被多个并发的 Channel 访问时导致问题。当然，这个简单的问题可以通过使 channelRead() 方法变为同步
     * 方法来修正。
     *
     * PS：主要的问题在于，对于其所持有的状态的修改并不是线程安全的，比如也可以通过使用 AtomicInteger 来规避这个问题。
     *
     * 总之，只应该在确定了你的 ChannelHandler 是线程安全的时才使用 @Sharable 注解。
     *
     * 为何要共享同一个 ChannelHandler：在多个 ChannelPipeline 中安装同一个 ChannelHandler 的一个常见的原因是用于
     * 收集跨越多个 Channel 的统计信息。
     */
    public static void main(String[] args) {

    }

}
