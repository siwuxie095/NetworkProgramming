package com.siwuxie095.network.chapter8th.example1st;

/**
 * @author Jiajing Li
 * @date 2020-11-21 20:34:15
 */
public class Main {

    /**
     * 引导
     *
     * 在深入地学习了 ChannelPipeline、ChannelHandler 和 EventLoop 之后，接下来的问题可能是：如何将这些部分
     * 组织起来，成为一个可实际运行的应用程序呢？
     *
     * 答案是：引导（Bootstrapping）。简单来说，引导一个应用程序是指对它进行配置，并使它运行起来的过程 — 尽管该
     * 过程的具体细节可能并不如它的定义那样简单，尤其是对于一个网络应用程序来说。
     *
     * 和 Netty 对应用程序体系架构的做法一致（即 分层抽象），Netty 处理引导的方式使你的应用程序（即 应用程序的
     * 逻辑或实现）和网络层相隔离，无论它是客户端还是服务器。所有的框架组件都将会在后台结合在一起并且启用。
     */
    public static void main(String[] args) {

    }

}
