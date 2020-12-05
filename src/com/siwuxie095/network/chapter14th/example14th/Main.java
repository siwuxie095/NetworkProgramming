package com.siwuxie095.network.chapter14th.example14th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 12:08:12
 */
public class Main {

    /**
     * Firebase 小结
     *
     * 在 Firebase 的实时数据同步服务的服务器端架构中，Netty 扮演了不可或缺的角色。它使得可以支持一个异构的客户端
     * 生态系统，其中包括了各种各样的浏览器，以及完全由 Firebase 控制的客户端。使用 Netty，Firebase 可以在每个服
     * 务器上每秒钟处理数以万计的消息。Netty 之所以非常了不起，有以下几个原因。
     * （1）它很快。开发原型只需要几天时间，并且从来不是生产瓶颈。
     * （2）它的抽象层次具有良好的定位。Netty 提供了必要的细粒度控制，并且允许在控制流的每一步进行自定义。
     * （3）它支持在同一个端口上支撑多种协议。HTTP、WebSocket、长轮询以及独立的 TCP 协议。
     * （4）它的 GitHub 库是一流的。精心编写的 Javadoc 使得可以无障碍地利用它进行开发。
     * （5）它拥有一个非常活跃的社区。社区非常积极地修复问题，并且认真地考虑所有的反馈以及合并请求。此外，Netty 团
     * 队还提供了优秀的最新的示例代码。Netty 是一个优秀的、维护良 好的框架，而且它已经成为了构建和伸缩 Firebase
     * 的基础设施的基础要素。如果没有 Netty 的速度、控制、抽象以及了不起的团队，那么 Firebase 中的实时数据同步将
     * 无从谈起。
     */
    public static void main(String[] args) {

    }

}
