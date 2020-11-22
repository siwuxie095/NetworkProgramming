package com.siwuxie095.network.chapter9th.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-22 21:03:27
 */
public class Main {

    /**
     * EmbeddedChannel 概述
     *
     * 已经知道，可以将 ChannelPipeline 中的 ChannelHandler 实现链接在一起，以构建应用程序的业务逻辑。
     * 这种设计支持将任何潜在的复杂处理过程分解为小的可重用的组件，每个组件都将处理一个明确定义的任务或者
     * 步骤。这里还将展示它是如何简化测试的。
     *
     * Netty 提供了它所谓的 Embedded 传输，用于测试 ChannelHandler。这个传输是一种特殊的 Channel 实
     * 现 — EmbeddedChannel — 的功能，这个实现提供了通过 ChannelPipeline 传播事件的简便方法。
     *
     * 这个想法是直截了当的：将入站数据或者出站数据写入到 EmbeddedChannel 中，然后检查是否有任何东西到达
     * 了 ChannelPipeline 的尾端。以这种方式，你便可以确定消息是否已经被编码或者被解码过了，以及是否触发
     * 了任何的 ChannelHandler 动作。
     *
     * 如下列出了 EmbeddedChannel 的相关方法。
     *
     * （1）writeInbound(Object... msgs)：
     * 将入站消息写到 EmbeddedChannel 中。如果可以通过 readInbound() 方法从 EmbeddedChannel 中读取
     * 数据，则返回 true。
     *
     * （2）readInbound()：
     * 从 EmbeddedChannel 中读取一个入站消息。任何返回的东西都穿越了整个 ChannelPipeline。如果没有任
     * 何可供读取的，则返回 null。
     *
     * （3）writeOutbound(Object... msgs)：
     * 将出站消息写到 EmbeddedChannel 中。如果现在可以通过 readOutbound() 方法从 EmbeddedChannel
     * 中读取到什么东西，则返回 true。
     *
     * （4）readOutbound()：
     * 从 EmbeddedChannel 中读取一个出站消息。任何返回的东西都穿越了整个 ChannelPipeline。如果没有任
     * 何可供读取的，则返回 null。
     *
     * （5）finish()：
     * 将 EmbeddedChannel 标记为完成，并且如果有可被读取的入站数据或者出站数据，则返回 true。这个方法
     * 还将会调用 EmbeddedChannel 上的 close() 方法。
     *
     * 入站数据由 ChannelInboundHandler 处理，代表从远程节点读取的数据。出站数据由
     * ChannelOutboundHandler 处理，代表将要写到远程节点的数据。根据你要测试的 ChannelHandler，你
     * 将使用 *Inbound() 或者 *Outbound() 方法对，或者兼而有之。
     *
     * 使用 EmbeddedChannel 的方法，数据是这样流经 ChannelPipeline 的：
     * 你可以使用 writeOutbound() 方法将消息写到 Channel 中，并通过 ChannelPipeline 沿着出站的方向
     * 传递。随后，你可以使用 readOutbound() 方法来读取已被处理过的消息，以确定结果是否和预期一样。类似
     * 地，对于入站数据，你需要使用 writeInbound() 和 readInbound() 方法。
     */
    public static void main(String[] args) {

    }

}
