package com.siwuxie095.network.chapter8th.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-21 20:39:57
 */
public class Main {

    /**
     * Bootstrap 类
     *
     * 引导类的层次结构包括一个抽象的父类和两个具体的引导子类，如下：
     * （1）AbstractBootstrap 抽象类实现自 Cloneable 接口；
     * （2）Bootstrap 类（即 客户端引导）继承自 AbstractBootstrap 抽象类；
     * （3）ServerBootstrap 类（即 服务器引导）继承自 AbstractBootstrap 抽象类。
     *
     * 相对于将具体的引导类分别看作用于服务器和客户端的引导来说，记住它们的本意是用来支撑不同的应用程序的功能的将有所裨益。
     * 也就是说，服务器致力于使用一个父 Channel 来接受来自客户端的连接，并创建子 Channel 以用于它们之间的通信。而客户端
     * 将最可能只需要一个单独的、没有父 Channel 的 Channel 来用于所有的网络交互。（这也适用于无连接的传输协议，如 UDP，
     * 因为它们并不是每个连接都需要一个单独的 Channel）
     *
     * Netty 的组件都参与了引导的过程，而且其中一些在客户端和服务器都有用到。两种应用程序类型之间通用的引导步骤由
     * AbstractBootstrap 处理，而特定于客户端或者服务器的引导步骤则分别由 Bootstrap 或 ServerBootstrap 处理。
     *
     * 为什么引导类是 Cloneable 的：
     *
     * 你有时可能会需要创建多个具有类似配置或者完全相同配置的 Channel。为了支持这种模式而又不需要为每个 Channel 都创建
     * 并配置一个新的引导类实例，AbstractBootstrap 被标记为了 Cloneable。在一个已经配置完成的引导类实例上调用 clone()
     * 方法将返回另一个可以立即使用的引导类实例。
     *
     * PS：关于 Cloneable 可参见 https://docs.oracle.com/javase/8/docs/api/java/lang/Cloneable.html
     *
     * 注意，这种方式只会创建引导类实例的 EventLoopGroup 的一个浅拷贝，所以，EventLoopGroup 将在所有克隆的 Channel
     * 实例之间共享。这是可以接受的，因为通常这些克隆的 Channel 的生命周期都很短暂，一个典型的场景是：创建一个 Channel
     * 以进行一次 HTTP 请求。
     *
     * AbstractBootstrap 类的完整声明是：
     *
     * public abstract class AbstractBootstrap<B extends AbstractBootstrap<B, C>, C extends Channel> implements Cloneable
     *
     * 在这个签名中，子类型 B 是其父类型的一个类型参数，因此可以返回到运行时实例的引用，以支持方法的链式调用（也就是所谓的
     * 流式语法）。
     *
     * 其子类的声明如下：
     *
     * public class Bootstrap extends AbstractBootstrap<Bootstrap, Channel>
     *
     * 和
     *
     * public class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel>
     */
    public static void main(String[] args) {

    }

}
