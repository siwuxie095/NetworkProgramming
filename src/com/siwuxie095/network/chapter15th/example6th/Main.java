package com.siwuxie095.network.chapter15th.example6th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 19:29:38
 */
public class Main {

    /**
     * Nifty 异步客户端的设计
     *
     * Nifty 的客户端开发主要集中在异步客户端上。Nifty 实际上也提供了一个针对 Thrift 的同步传输接口的 Netty 实现，
     * 但是它的使用相当受限，因为相对于 Thrift 所提供的标准的套接字传输，它并没有太多的优势。因此，用户应该尽可能地
     * 使用异步客户端。
     *
     *
     * 1、流水线化
     *
     * Thrift 库拥有它自己的基于 NIO 的异步客户端实现，但是他们想要的一个特性是请求的流水线化。流水线化是一种在同一
     * 连接上发送多个请求，而不需要等待其响应的能力。如果服务器有空闲的工作线程，那么它便可以并行地处理这些请求，但是
     * 即使所有的工作线程都处于忙绿状态，流水线化仍然可以有其他方面的裨益。服务器将会花费更少的时间来等待读取数据，而
     * 客户端则可以在一个单一的 TCP 数据包里一起发送多个小请求，从而更好地利用网络带宽。
     *
     * 使用 Netty，流水线化水到渠成。Netty 做了所有管理各种 NIO 选择键的状态的艰涩的工作，Nifty 则可以专注于解码
     * 请求以及编码响应。
     *
     *
     * 2、多路复用
     *
     * 随着基础设施的增长，他们开始看到在他们的服务器上建立起来了大量的连接。多路复用（为所有的连接来自于单一的来源的
     * Thrift 客户端共享连接）可以帮助减轻这种状况。但是在需要按序响应的客户端连接上进行多路复用会导致一个问题：该连
     * 接上的客户端可能会招致额外的延迟，因为它的响应必须要跟在对应于其他共享该连接的请求的响应之后。
     *
     * 基本的解决方案也相当简单：Thrift 已经在发送每个消息时都捎带了一个序列标识符，所以为了支持无序响应，只需要客户
     * 端 Channel 维护一个从序列 ID 到响应处理器的一个映射，而不是一个使用队列。
     *
     * 但是问题的关键在于，在标准的同步 Thrift 客户端中，协议层将负责从消息中提取序列标识符，再由协议层协议调用传输
     * 层，而不是其他的方式。
     *
     * 对于同步客户端来说，这种简单的流程能够良好地工作，其协议层可以在传输层上等待，以实际接收响应，但是对于异步客户
     * 端来说，其控制流就变得更加复杂了。客户端调用将会被分发到 Swift 库中，其将首先要求协议层将请求编码到一个缓冲区，
     * 然后将编码请求缓冲区传递给 Nifty 的 Channel 以便被写出。当该 Channel 收到来自服务器的响应时，它将会通知
     * Swift 库，其将再次使用协议层以对响应缓冲区进行解码。
     */
    public static void main(String[] args) {

    }

}
