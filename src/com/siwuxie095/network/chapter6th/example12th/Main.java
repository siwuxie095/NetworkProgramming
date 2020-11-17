package com.siwuxie095.network.chapter6th.example12th;

/**
 * @author Jiajing Li
 * @date 2020-11-17 20:35:14
 */
public class Main {

    /**
     * 使用 ChannelHandlerContext
     *
     * 这里将讨论 ChannelHandlerContext 的用法，以及存在于 ChannelHandlerContext、Channel 和 ChannelPipeline
     * 上的方法的行为。
     *
     * Channel、ChannelPipeline、ChannelHandler 以及 ChannelHandlerContext 之间的关系如下：
     * （1）Channel 被绑定到 ChannelPipeline。
     * （2）和 Channel 绑定的 ChannelPipeline 包含了所有的 ChannelHandler。
     * （3）当把 ChannelHandler 添加到 ChannelPipeline 时，ChannelHandlerContext 将会被创建。
     *
     * 以 WriteHandlers 中的 writeViaChannel() 方法为例，将通过 ChannelHandlerContext 获取到 Channel 的引用。
     * 调用 Channel 上的 write() 方法将会导致写入事件从尾端到头部地流经 ChannelPipeline。
     *
     * 以 WriteHandlers 中的 writeViaChannelPipeline() 方法为例，但是这一次是写入 ChannelPipeline。再次看到，
     * （到 ChannelPipeline 的）引用是通过 ChannelHandlerContext 获取的。
     *
     * 不难发现，上述两个示例的事件流是一样的。重要的是要注意到，虽然被调用的 Channel 或 ChannelPipeline 上的 write()
     * 方法将一直传播事件通过整个 ChannelPipeline，但是在 ChannelHandler 的级别上，事件从一个 ChannelHandler 到
     * 下一个 ChannelHandler 的移动是由 ChannelHandlerContext 上的调用完成的。
     *
     * 通过 Channel 或者 ChannelPipeline 进行的事件传播，如下：
     * （1）事件被传递给了 ChannelPipeline 中的第一个 ChannelHandler。
     * （2）通过使用与之相关联的 ChannelHandlerContext，ChannelHandler 将事件传递给了 ChannelPipeline 中的下一
     * 个 ChannelHandler。
     *
     * 为什么会想要从 ChannelPipeline 中的某个特定点开始传播事件呢？
     * （1）为了减少将事件传经对它不感兴趣的 ChannelHandler 所带来的开销。
     * （2）为了避免将事件传经那些可能会对它感兴趣的 ChannelHandler。
     *
     * 要想调用从某个特定的 ChannelHandler 开始的处理过程，必须获取到在（ChannelPipeline）该 ChannelHandler 之前
     * 的 ChannelHandler 所关联的 ChannelHandlerContext。这个 ChannelHandlerContext 将调用和它所关联的
     * ChannelHandler 之后的 ChannelHandler。
     *
     * 以 WriteHandlers 中的 writeViaChannelHandlerContext() 方法为例，说明了这种用法。消息将从下一个
     * ChannelHandler 开始流经 ChannelPipeline，绕过了前面所有的 ChannelHandler。
     *
     * 上面所描述的用例是常见的，对于调用特定的 ChannelHandler 实例上的操作尤其有用。
     */
    public static void main(String[] args) {

    }

}
