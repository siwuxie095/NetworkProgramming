package com.siwuxie095.network.chapter11th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-28 18:01:18
 */
public class Main {

    /**
     * 构建基于 Netty 的 HTTP/HTTPS 应用程序
     *
     * HTTP/HTTPS 是最常见的协议套件之一，并且随着智能手机的成功，它的应用也日益广泛，因为对于任何公司来说，拥有一个
     * 可以被移动设备访问的网站几乎是必须的。这些协议也被用于其他方面。许多组织导出的用于和他们的商业合作伙伴通信的
     * WebService API 一般也是基于 HTTP(S) 的。
     *
     * 下面来看看 Netty 提供的 ChannelHandler，你可以用它来处理 HTTP 和 HTTPS 协议，而不必编写自定义的编解码器。
     *
     *
     *
     * HTTP 解码器、编码器和编解码器
     *
     * HTTP 是基于请求/响应模式的：客户端向服务器发送一个 HTTP 请求，然后服务器将会返回一个 HTTP 响应。Netty 提供
     * 了多种编码器和解码器以简化对这个协议的使用。
     *
     * HTTP 请求的组成部分如下：
     * （1）HTTP 请求的第一个部分包含了 HTTP 的头部信息。
     * （2）HttpContent 包含了数据，后面可能还跟着一个或者多个 HttpContent 部分。
     * （3）LastHttpContent 标记了该 HTTP 请求的结束，可能还包含了尾随的 HTTP 头部信息。
     *
     * HTTP 响应的组成部分如下：
     * （1）HTTP 响应的第一个部分包含了 HTTP 的头部信息。
     * （2）HttpContent 包含了数据，后面可能还跟着一个或者多个 HttpContent 部分。
     * （3）LastHttpContent 标记了该 HTTP 响应的结束，可能还包含了尾随的 HTTP 头部信息。
     *
     * 一个 HTTP 请求/响应可能由多个数据部分组成，并且它总是以一个 LastHttpContent 部分作为结束。FullHttpRequest
     * 和 FullHttpResponse 消息是特殊的子类型，分别代表了完整的请求和响应。所有类型的 HTTP 消息（FullHttpRequest、
     * LastHttpContent 等）都实现了 HttpObject 接口。
     *
     * 如下概要地介绍了处理和生成这些消息的 HTTP 解码器和编码器。
     * （1）HttpRequestEncoder：将 HttpRequest、HttpContent 和 LastHttpContent 消息编码为字节。
     * （2）HttpResponseEncoder：将 HttpResponse、HttpContent 和 LastHttpContent 消息编码为字节。
     * （3）HttpRequestDecoder：将字节解码为 HttpRequest、HttpContent 和 LastHttpContent 消息。
     * （4）HttpResponseDecoder：将字节解码为 HttpResponse、HttpContent 和 LastHttpContent 消息。
     *
     * 以 HttpPipelineInitializer 为例，展示了将 HTTP 支持添加到你的应用程序中。这看起来是多么简单 — 几乎只需要
     * 将正确的 ChannelHandler 添加到 ChannelPipeline 中。
     *
     *
     *
     * 聚合 HTTP 消息
     *
     * 在 ChannelInitializer 将 ChannelHandler 安装到 ChannelPipeline 中之后，你便可以处理不同类型的
     * HttpObject 消息了。但是由于 HTTP 的请求和响应可能由许多部分组成，因此你需要聚合它们以形成完整的消息。
     * 为了消除这项繁琐的任务，Netty 提供了一个聚合器，它可以将多个消息部分合并为 FullHttpRequest 或者
     * FullHttpResponse 消息。通过这样的方式，你将总是看到完整的消息内容。
     *
     * 由于消息分段需要被缓冲，直到可以转发一个完整的消息给下一个 ChannelInboundHandler，所以这个操作有轻微的开销。
     * 其所带来的好处便是你不必关心消息碎片了。
     *
     * 引入这种自动聚合机制只不过是向 ChannelPipeline 中添加另外一个 ChannelHandler 罢了。
     *
     * 以 HttpAggregatorInitializer 为例，展示了如何做到这一点。
     *
     *
     *
     * HTTP 压缩
     *
     * 当使用 HTTP 时，建议开启压缩功能以尽可能多地减小传输数据的大小。虽然压缩会带来一些 CPU 时钟周期上的开销，但是
     * 通常来说它都是一个好主意，特别是对于文本数据来说。
     *
     * Netty 为压缩和解压缩提供了 ChannelHandler 实现，它们同时支持 gzip 和 deflate 编码。
     *
     *
     * HTTP 请求的头部信息：
     * 客户端可以通过提供以下头部信息来指示服务器它所支持的压缩格式：
     *
     * GET /encrypted-area
     * HTTP/1.1 Host: www.example.com
     * Accept-Encoding: gzip, deflate
     *
     * 然而，需要注意的是，服务器没有义务压缩它所发送的数据。
     *
     * 以 HttpCompressionInitializer 为例，展示了如何自动压缩 HTTP 消息。
     *
     *
     * 压缩及其依赖：
     * 如果你正在使用的是 JDK 6 或者更早的版本，那么你需要将 JZlib（www.jcraft.com/jzlib/）添加到 CLASSPATH 中
     * 以支持压缩功能。
     *
     * 对于 Maven，请添加以下依赖项：
     * <dependency>
     * 	<groupId>com.jcraft</groupId>
     * 	<artifactId>jzlib</artifactId>
     * 	<version>1.1.3</version>
     * </dependency>
     *
     *
     *
     * 使用 HTTPS
     *
     * 以 HttpsCodecInitializer 为例，启用 HTTPS 只需要将 SslHandler 添加到 ChannelPipeline 的 ChannelHandler
     * 组合中。
     *
     * 这是一个很好的例子，说明了 Netty 的架构方式是如何将代码重用变为杠杆作用的。只需要简单地将一个 ChannelHandler
     * 添加到 ChannelPipeline 中，便可以提供一项新功能，甚至像加密这样重要的功能都能提供。
     *
     *
     *
     * WebSocket
     *
     * Netty 针对基于 HTTP 的应用程序的广泛工具包中包括了对它的一些最先进的特性的支持。这里将探讨 WebSocket，一种
     * 在 2011 年被互联网工程任务组（IETF）标准化的协议。
     *
     * WebSocket 解决了一个长期存在的问题：既然底层的协议（HTTP）是一个请求/响应模式的交互序列，那么如何实时地发布
     * 信息呢？AJAX 提供了一定程度上的改善，但是数据流仍然是由客户端所发送的请求驱动的。还有其他的一些或多或少的取巧
     * 方式（Comet 就是一个例子：https://en.wikipedia.org/wiki/Comet_%28programming%29），但是最终它们仍然
     * 属于扩展性受限的变通之法。
     *
     * WebSocket 规范以及它的实现代表了对一种更加有效的解决方案的尝试。简单地说，WebSocket 提供了 "在一个单个的
     * TCP 连接上提供双向的通信 ...... 结合WebSocket API ...... 它为网页和远程服务器之间的双向通信提供了一种替代
     * HTTP 轮询的方案"。具体可参见 RFC 6455，WebSocket 协议，http://tools.ietf.org/html/rfc6455。
     *
     * 也就是说，WebSocket 在客户端和服务器之间提供了真正的双向数据交换。值得一提的是，尽管最早的实现仅限于文本数据，
     * 但是现在已经不是问题了，WebSocket 现在可以用于传输任意类型的数据，很像普通的套接字。
     *
     * 要想向你的应用程序中添加对于 WebSocket 的支持，你需要将适当的客户端或者服务器 WebSocket ChannelHandler
     * 添加到 ChannelPipeline 中。这个类将处理由 WebSocket 定义的称为帧的特殊消息类型。
     *
     * 总体而言，WebSocketFrame 可以被归类为数据帧或者控制帧。如下：
     * （1）BinaryWebSocketFrame：数据帧：二进制数据。
     * （2）TextWebSocketFrame：数据帧：文本数据。
     * （3）ContinuationWebSocketFrame：数据帧：属于上一个 BinaryWebSocketFrame 或者 TextWebSocketFrame
     * 的文本的或者二进制数据。
     * （4）CloseWebSocketFrame：控制帧：一个 CLOSE 请求、关闭的状态码以及关闭的原因。
     * （5）PingWebSocketFrame：控制帧：请求一个 PongWebSocketFrame。
     * （6）PongWebSocketFrame：控制帧：对 PingWebSocketFrame 请求的响应。
     *
     * 因为 Netty 主要是一种服务器端的技术，所以在这里重点创建 WebSocket 服务器。以 WebSocketServerInitializer
     * 为例，展示了一个使用 WebSocketServerProtocolHandler 的简单示例，这个类处理协议升级握手，以及 3 种控制帧：
     * Close、Ping 和 Pong。Text 和 Binary 数据帧将会被传递给下一个（由你实现的）ChannelHandler 进行处理。
     *
     * PS：关于 WebSocket 的客户端示例，可参考 Netty 源代码中所包含的例子：
     * https://github.com/netty/netty/tree/4.1/example/src/main/java/io/netty/example/http/websocketx/client
     *
     * 保护 WebSocket：
     * 要想为 WebSocket 添加安全性，只需要将 SslHandler 作为第一个 ChannelHandler 添加到 ChannelPipeline 中。
     */
    public static void main(String[] args) {

    }

}
