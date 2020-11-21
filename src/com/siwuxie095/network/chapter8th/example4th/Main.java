package com.siwuxie095.network.chapter8th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-21 22:33:55
 */
public class Main {

    /**
     * 引导服务器
     *
     * 下面将从 ServerBootstrap API 的概要视图开始对服务器引导过程的概述。然后，将会探讨引导服务器过程中所涉及
     * 的几个步骤，以及几个相关的主题，包含从一个 ServerChannel 的子 Channel 中引导一个客户端这样的特殊情况。
     *
     *
     *
     * 1、ServerBootstrap 类
     *
     * 如下是 ServerBootstrap 类的方法：
     *
     * （1）group：
     * 设置 ServerBootstrap 要用的 EventLoopGroup。这个 EventLoopGroup 将用于 ServerChannel 和被接受的
     * 子 Channel 的 I/O 处理。
     *
     * （2）channel：
     * 设置将要被实例化的 ServerChannel 类。
     *
     * （3）channelFactory：
     * 如果不能通过默认的构造函数（这里指无参数的构造函数）创建 Channel，那么可以提供一个 ChannelFactory。
     *
     * （4）localAddress：
     * 指定 ServerChannel 应该绑定到的本地地址。如果没有指定，则将由操作系统使用一个随机地址。或者，可以通过
     * bind() 方法来指定该 localAddress。
     *
     * （5）option：
     * 指定要应用到新创建的 ServerChannel 的 ChannelConfig 的 ChannelOption。这些选项将会通过 bind()
     * 方法设置到 Channel。在 bind() 方法被调用之后，设置或者改变 ChannelOption 都不会有任何的效果。所支持的
     * ChannelOption 取决于所使用的 Channel 类型。
     *
     * （6）childOption：
     * 指定当子 Channel 被接受时，应用到子 Channel 的 ChannelConfig 的 ChannelOption。所支持的 ChannelOption
     * 取决于所使用的 Channel 的类型。
     *
     * （7）attr：
     * 指定 ServerChannel 上的属性，属性将会通过 bind() 方法设置给 Channel。 在调用 bind() 方法之后改变它们
     * 将不会有任何的效果。
     *
     * （8）childAttr：
     * 将属性设置给已经被接受的子 Channel。接下来的调用将不会有任何的效果。
     *
     * （9）handler：
     * 设置被添加到 ServerChannel 的 ChannelPipeline 中的 ChannelHandler。更加常用的方法参见 childHandler()。
     *
     * （10）childHandler：
     * 设置将被添加到已被接受的子 Channel 的 ChannelPipeline 中的 ChannelHandler。handler() 方法和
     * childHandler() 方法之间的区别是：前者所添加的 ChannelHandler 由接受子 Channel 的 ServerChannel
     * 处理，而后者所添加的 ChannelHandler 将由已被接受的子 Channel 处理，其代表一个绑定到远程节点的套接字。
     *
     *
     *
     * 2、引导服务器示例
     *
     * 不难发现，上面列出了一些在引导客户端中不存在的方法：childHandler()、 childAttr()和 childOption()。
     * 这些调用支持特别用于服务器应用程序的操作。具体来说，ServerChannel 的实现负责创建子 Channel，这些子
     * Channel 代表了已被接受的连接。因此，负责引导 ServerChannel 的 ServerBootstrap 提供了这些方法，以
     * 简化将设置应用到已被接受的子 Channel 的 ChannelConfig 的任务。
     *
     * ServerBootstrap 在 bind() 方法被调用时创建了一个 ServerChannel， 并且该 ServerChannel 管理了多
     * 个子 Channel。过程如下：
     * （1）当 bind() 方法被调用时，将会创建一个 ServerChannel；
     * （2）当连接被接受时，ServerChannel 将会创建一个新的子 Channel。
     *
     * 以 BootstrapServer 为例，展示了服务器的引导过程。
     */
    public static void main(String[] args) {

    }

}
