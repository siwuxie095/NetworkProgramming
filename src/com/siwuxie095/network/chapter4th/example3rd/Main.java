package com.siwuxie095.network.chapter4th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-09 21:56:20
 */
public class Main {

    /**
     * 传输 API
     *
     * 传输 API 的核心是 Channel 接口，它被用于所有的 I/O 操作。Channel 类的层次结构如下：
     * （1）Channel 接口继承自 Comparable 接口和 AttributeMap 接口；
     * （2）ServerChannel 接口继承自 Channel 接口；
     * （3）ChannelPipeline 接口中包含了 Channel 接口；
     * （4）ChannelConfig 接口中包含了 Channel 接口；
     * （5）AbstractChannel 抽象类实现自 Channel 接口。
     *
     * 每个 Channel 都将会被分配一个 ChannelPipeline 和 ChannelConfig。ChannelConfig 包含了该 Channel 的所有
     * 配置设置，并且支持热更新。由于特定的传输可能具有独特的设置，所以它可能会实现一个 ChannelConfig 的子类型。
     *
     * 由于 Channel 是独一无二的，所以为了保证顺序将 Channel 声明为 java.lang.Comparable 的一个子接口。因此，如果
     * 两个不同的 Channel 实例都返回了相同的散列码，那么 AbstractChannel 中的 compareTo() 方法的实现将会抛出一个
     * Error。
     *
     * ChannelPipeline 持有所有将应用于入站和出站数据以及事件的 ChannelHandler 实例，这些 ChannelHandler 实现了
     * 应用程序用于处理状态变化以及数据处理的逻辑。
     *
     * ChannelHandler 的典型用途包括：
     * （1）将数据从一种格式转换为另一种格式；
     * （2）提供异常的通知；
     * （3）提供 Channel 变为活动（活跃）的或者非活动（非活跃）的通知；
     * （4）提供当 Channel 注册到 EventLoop 或者从 EventLoop 注销时的通知；
     * （5）提供有关用户自定义事件的通知。
     *
     * 关于拦截过滤器：
     * ChannelPipeline 实现了一种常见的设计模式：拦截过滤器（Intercepting Filter）。UNIX 管道是另外一个熟悉的例子：
     * 多个命令被链接在一起，其中一个命令的输出端将连接到命令行中下一个命令的输入端。
     *
     * 也可以根据需要通过添加或者移除 ChannelHandler 实例来修改 ChannelPipeline。通过利用 Netty 的这项能力可以构建
     * 出高度灵活的应用程序。例如，每当 STARTTLS 协议被请求时，可以简单地通过向 ChannelPipeline 添加一个适当的
     * ChannelHandler（SslHandler）来按需地支持 STARTTLS 协议。
     *
     * PS：STARTTLS 协议可参考 https://en.wikipedia.org/wiki/Opportunistic_TLS。
     *
     * 除了访问所分配的 ChannelPipeline 和 ChannelConfig 之外，也可以利用 Channel 的其他方法，其中最重要的列举如下：
     * （1）eventLoop：返回分配给 Channel 的 EventLoop；
     * （2）pipeline：返回分配给 Channel 的 ChannelPipeline；
     * （3）isActive：如果 Channel 是活动（活跃）的，则返回 true。活动（活跃）的意义可能依赖于底层的传输。例如，一个
     * Socket 传输一旦连接到了远程节点便是活动（活跃）的，而一个 Datagram 传输一旦被打开便是活动（活跃）的。
     * （4）localAddress：返回本地的 SokcetAddress；
     * （5）remoteAddress：返回远程的 SocketAddress；
     * （6）write：将数据写到远程节点。这个数据将被传递给 ChannelPipeline，并且排队直到它被冲刷；
     * （7）flush：将之前已写的数据冲刷到底层传输，如一个 Socket；
     * （8）writeAndFlush：一个简便的方法，等同于调用 write() 并接着调用 flush()。
     *
     * Netty 所提供的广泛功能只依赖于少量的接口。这意味着，可以对应用程序逻辑进行重大的修改，而又无需大规模地重构代码库。
     *
     * 考虑一下写数据并将其冲刷到远程节点这样的常规任务。ChannelOperationExamples 的 writingToChannel() 方法演示
     * 了使用 Channel.writeAndFlush() 来实现这一目的。
     *
     * Netty 的 Channel 实现是线程安全的，因此可以存储一个到 Channel 的引用，并且每当需要向远程节点写数据时，都可以
     * 使用它，即使当时许多线程都在使用它。ChannelOperationExamples 的 writingToChannelFromManyThreads() 方法
     * 展示了一个多线程写数据的简单例子。需要注意的是，消息将会被保证按顺序发送。
     */
    public static void main(String[] args) {

    }

}
