package com.siwuxie095.network.chapter14th.example10th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 09:46:50
 */
public class Main {

    /**
     * Firebase 的架构
     *
     * Firebase 允许开发者使用两层体系结构来上线运行应用程序。开发者只需要简单地导入 Firebase 库，并编写客户端代码。
     * 数据将以 JSON 格式暴露给开发者的代码，并且在本地进行缓存。该库处理了本地高速缓存和存储在 Firebase 服务器上的
     * 主副本（master copy）之间的同步。对于任何数据进行的更改都将会被实时地同步到与 Firebase 相连接的潜在的数十万
     * 个客户端上。
     *
     * Firebase 的服务器接收传入的数据更新，并将它们立即同步给所有注册了对于更改的数据感兴趣的已经连接的客户端。为了
     * 启用状态更改的实时通知，客户端将会始终保持一个到 Firebase 的活动连接。该连接的范围是：从基于单个 Netty Channel
     * 的抽象到基于多个 Channel 的抽象，甚至是在客户端正在切换传输类型时的多个并存的抽象。
     *
     * 因为客户端可以通过多种方式连接到 Firebase，所以保持连接代码的模块化很重要。Netty 的 Channel 抽象对于 Firebase
     * 集成新的传输来说简直是梦幻般的构建块。此外，流水线和处理器模式（指 ChannelPipeline 和 ChannelHandler）使得
     * 可以简单地把传输相关的细节隔离开来，并为应用程序代码提供一个公共的消息流抽象。同样，这也极大地简化了添加新的协议
     * 支持所需要的工作。Firebase 只通过简单地添加几个新的 ChannelHandler 到 ChannelPipeline 中，便添加了对一种
     * 二进制传输的支持。对于实现客户端和服务器之间的实时连接而言，Netty 的速度、抽象的级别以及细粒度的控制都使得它成
     * 为了一个的卓绝（卓越）的框架。
     */
    public static void main(String[] args) {

    }

}
