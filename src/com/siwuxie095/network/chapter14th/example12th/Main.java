package com.siwuxie095.network.chapter14th.example12th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 11:09:27
 */
public class Main {

    /**
     * HTTP 1.1 keep-alive 和流水线化
     *
     * 通过 HTTP 1.1 keep-alive 特性，可以在同一个连接上发送多个请求到服务器。这使得 HTTP 流水线化 —— 可以发送新的
     * 请求而不必等待来自服务器的响应，成为了可能。实现对于 HTTP 流水线化以及 keep-alive 特性的支持通常是直截了当的，
     * 但是当混入了长轮询之后，它就明显变得更加复杂起来。
     *
     * 如果一个长轮询请求紧跟着一个 REST（表征状态转移）请求，那么将有一些注意事项需要被考虑在内，以确保浏览器能够正确
     * 工作。一个 Channel 可能会混和异步消息（长轮询请求）和同步消息（REST 请求）。当一个 Channel 上出现了一个同步
     * 请求时，Firebase 必须按顺序同步响应该 Channel 中所有之前的请求。例如，如果有一个未完成的长轮询请求，那么在处
     * 理该 REST 请求之前，需要使用一个空操作对该长轮询传输进行响应。
     *
     * 如果浏览器有多个打开的连接，并且正在使用长轮询，那么它将重用这些连接来处理来自这两个打开的标签页的消息。对于长轮
     * 询请求来说，这是很困难的，并且还需要妥善地管理一个 HTTP 请求队列。长轮询请求可以被中断，但是被代理的请求却不能。
     * Netty 使服务于多种类型的请求很轻松。
     * （1）静态的 HTML 页面 —— 缓存的内容，可以直接返回而不需要进行处理，例子包括一个单页面的 HTTP 应用程序、
     * robots.txt 和 crossdomain.xml。
     * （2）REST 请求 —— Firebase 支持传统的 GET、POST、PUT、DELETE、PATCH 以及 OPTIONS 请求。
     * （3）WebSocket —— 浏览器和 Firebase 服务器之间的双向连接，拥有它自己的分帧协议。
     * （4）长轮询 —— 这些类似于 HTTP 的 GET 请求，但是应用程序的处理方式有所不同。
     * （5）被代理的请求 —— 某些请求不能由接收它们的服务器处理。在这种情况下，Firebase 将会把这些请求代理到集群中正确
     * 的服务器。以便最终用户不必担心数据存储的具体位置。这些类似于 REST 请求，但是代理服务器处理它们的方式有所不同。
     * （6）通过 SSL 的原始字节 —— 一个简单的 TCP 套接字，运行 Firebase 自己的分帧协议，并且优化了握手过程。
     *
     * Firebase 使用 Netty 来设置好它的 ChannelPipeline 以解析传入的请求，并随后适当地重新配置 ChannelPipeline
     * 剩余的其他部分。在某些情况下，如 WebSocket 和原始字节，一旦某个特定类型的请求被分配给某个 Channel 之后，它就
     * 会在它的整个生命周期内保持一致。在其他情况下，如各种 HTTP 请求，该分配则必须以每个消息为基础进行赋值。同一个
     * Channel 可以处理 REST 请求、长轮询请求以及被代理的请求。
     */
    public static void main(String[] args) {

    }

}
