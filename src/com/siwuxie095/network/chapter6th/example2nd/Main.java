package com.siwuxie095.network.chapter6th.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-15 10:48:23
 */
public class Main {

    /**
     * Channel 的生命周期
     *
     * Channel 接口定义了一组和 ChannelInboundHandler API 密切相关的简单但功能强大的状态模型。
     *
     * Channel 的 4 个状态如下：
     * （1）ChannelUnregistered：Channel 已经被创建，但还未注册到 EventLoop。
     * （2）ChannelRegistered：Channel 已经被注册到了 EventLoop。
     * （3）ChannelActive：Channel 处于活动（活跃）状态（已经连接到它的远程节点）。它现在可以接收和发送数据了。
     * （4）ChannelInactive：Channel 没有连接到远程节点。
     *
     * Channel 的正常生命周期如下：
     * （2）->（3）->（4）->（1）
     *
     * 当这些状态发生改变时，将会生成对应的事件。这些事件将会被转发给 ChannelPipeline 中的 ChannelHandler，
     * 其可以随后对它们做出响应。
     */
    public static void main(String[] args) {

    }

}
