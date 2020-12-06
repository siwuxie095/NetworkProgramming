package com.siwuxie095.network.chapter15th.example12th;

/**
 * @author Jiajing Li
 * @date 2020-12-06 10:36:15
 */
public class Main {

    /**
     * Finagle 的诞生
     *
     * 为了构建出这个新的架构，他们需要一个高性能的、容错的、协议不可知的、异步的 RPC 框架。在面向服务的架构中，
     * 服务花费了它们大部分的时间来等待来自其他上游的服务的响应。使用异步的库使得服务可以并发地处理请求，并且充
     * 分地利用硬件资源。尽管 Finagle 可以直接建立在 NIO 之 上，但是 Netty 已经解决了许多他们可能会遇到的问
     * 题，并且它提供了一个简洁、清晰的 API。
     *
     * Twitter 构建在几种开源的协议之上，主要是 HTTP、Thrift、Memcached、MySQL 以及 Redis。他们的网络栈
     * 需要具备足够的灵活性，能够和任何的这些协议进行交流，并且具备足够的可扩展性，以便他们可以方便地添加更多的
     * 协议。Netty 并没有绑定任何特定的协议。向它添加协议就像创建适当的 ChannelHandler 一样简单。这种扩展性
     * 也催生了许多社区驱动的协议实现，包 括 SPDY、PostgreSQL、WebSockets、IRC 以及 AWS。
     *
     * Netty 的连接管理以及协议不可知的特性为构建 Finagle 提供了绝佳的基础。但是他们也有一些其他的 Netty 不
     * 能开箱即满足的需求，因为那些需求都更高级。客户端需要连接到服务器集群，并且需要做跨服务器集群的负载均衡。
     * 所有的服务都需要暴露运行指标（请求率、延迟等），其可以为调试服务的行为提供有价值的数据。在面向服务的架构
     * 中，一个单一的请求都可能需要经过数十种服务，使得如果没有一个由 Dapper 启发的跟踪框架，调试性能问题几乎
     * 是不可能的。Finagle 正是为了解决这些问题而构建的。
     *
     * PS：有关 Dapper 的信息可以在 http://research.google.com/pubs/pub36356.html 找到。该分布式的跟
     * 踪框架是 Zipkin，可以在 https://github.com/twitter/zipkin 找到。
     */
    public static void main(String[] args) {

    }

}
