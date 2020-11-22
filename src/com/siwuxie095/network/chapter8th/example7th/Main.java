package com.siwuxie095.network.chapter8th.example7th;

/**
 * @author Jiajing Li
 * @date 2020-11-22 17:21:17
 */
public class Main {

    /**
     * 使用 Netty 的 ChannelOption 和属性
     *
     * 在每个 Channel 创建时都手动配置它可能会变得相当乏味。幸运的是，你不必这样做。相反，你可以使用 option() 方法来
     * 将 ChannelOption 应用到引导。你所提供的值将会被自动应用到引导所创建的所有 Channel。可用的 ChannelOption
     * 包括了底层连接的详细信息，如 keep-alive 或者超时属性以及缓冲区设置。
     *
     * Netty 应用程序通常与组织的专有软件集成在一起，而像 Channel 这样的组件可能甚至会在正常的 Netty 生命周期之外被
     * 使用。在某些常用的属性和数据不可用时，Netty 提供了 AttributeMap 抽象（一个由 Channel 和引导类提供的集合）以
     * 及 AttributeKey<T>（一个用于插入和获取属性值的泛型类）。使用这些工具，便可以安全地将任何类型的数据项与客户端和
     * 服务器 Channel（包含 ServerChannel 的子 Channel）相关联了。
     *
     * 例如，考虑一个用于跟踪用户和 Channel 之间的关系的服务器应用程序。这可以通过将用户的 ID 存储为 Channel 的一个
     * 属性来完成。类似的技术可以被用来基于用户的 ID 将消息路由给用户，或者关闭活动较少的 Channel。
     *
     * 以 BootstrapClientWithOptionsAndAttrs 为例，展示了如何使用 ChannelOption 来配置 Channel，以及如何使用
     * 属性来存储整型值。
     */
    public static void main(String[] args) {

    }

}
