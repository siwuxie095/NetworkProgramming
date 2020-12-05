package com.siwuxie095.network.chapter14th.example21th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 14:11:05
 */
public class Main {

    /**
     * Urban Airship 小结：跨越防火墙边界
     *
     * 已经介绍了两个在 Urban Airship 内部网络中每天都会使用 Netty 的场景。Netty 适合这些用途，并且工作得
     * 非常出色，但在 Urban Airship 内部的许多其他的组件中也有它作为脚手架存在的身影。
     *
     *
     * 1、内部的 RPC 框架
     *
     * Netty 一直都是 Urban Airship 内部的 RPC 框架的核心，其一直都在不断进化。今天，这个框架每秒钟可以处
     * 理数以十万计的请求，并且拥有相当低的延迟以及杰出的吞吐量。几乎每个由 Urban Airship 发出的 API 请求
     * 都经由了多个后端服务处理，而 Netty 正是所有这些服务的核心。
     *
     *
     * 2、负载和性能测试
     *
     * Netty 在 Urban Airship 已经被用于几个不同的负载测试框架和性能测试框架。例如，在测试设备消息服务时，
     * 为了模拟数百万的设备连接，Netty 和一个 Redis 实例（https://redis.io/）相结合使用，以最小的客户端
     * 足迹（负载）测试了端到端的消息吞吐量。
     *
     *
     * 3、同步协议的异步客户端
     *
     * 对于一些内部的使用场景，Urban Airship 一直都在尝试使用 Netty 来为典型的同步协议创建异步的客户端，
     * 包括如 Apache Kafka（http://kafka.apache.org/）以及 Memcached（http://memcached.org/）这
     * 样的服务。Netty 的灵活性使得能够很容易地打造天然异步的客户端，并且能够在真正的异步或同步的实现之间
     * 来回地切换，而不需要更改任何的上游代码。
     *
     *
     * 总而言之，Netty 一直都是 Urban Airship 服务的基石。其作者和社区都是极其出色的，并为任何需要在 JVM
     * 上进行网络通信的应用程序，创造了一个真正意义上的一流框架。
     */
    public static void main(String[] args) {

    }

}
