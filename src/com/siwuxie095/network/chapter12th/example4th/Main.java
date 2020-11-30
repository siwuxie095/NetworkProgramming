package com.siwuxie095.network.chapter12th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-30 08:13:17
 */
public class Main {

    /**
     * 添加 WebSocket 支持
     *
     * 在从标准的 HTTP 或者 HTTPS 协议切换到 WebSocket 时，将会使用一种称为升级握手的机制。因此 ，使用 WebSocket 的
     * 应用程序将始终以 HTTP/S 作为开始，然后再执行升级。这个升级动作发生的确切时刻特定于应用程序。它可能会发生在启动时，
     * 也可能会发生在请求了某个特定的 URL 之后。
     *
     * PS：关于升级握手，可参见 Mozilla 开发者网络，"Protocol upgrade mechanism"：
     * https://developer.mozilla.org/en-US/docs/HTTP/Protocol_upgrade_mechanism
     *
     * 应用程序将采用下面的约定：如果被请求的 URL 以 /ws 结尾，那么这里将会把该协议升级为 WebSocket。否则，服务器将使
     * 用基本的 HTTP/S。在连接已经升级完成之后，所有数据都将会使用 WebSocket 进行传输。
     *
     * 一如在 Netty 中一样，它由一组 ChannelHandler 实现。
     *
     *
     *
     * 处理 HTTP 请求
     *
     * 首先，这里将实现该处理 HTTP 请求的组件。这个组件将提供用于访问聊天室并显示由连接的客户端发送的消息的网页。以
     * HttpRequestHandler 为例，其扩展了 SimpleChannelInboundHandler 以处理 FullHttpRequest 消息。需要注意的是，
     * channelRead0() 方法的实现是如何转发任何目标 URI 为 /ws 的请求的。
     *
     * 如果该 HTTP 请求指向了地址为 /ws 的 URI，那么 HttpRequestHandler 将调用 FullHttpRequest 对象上的 retain()
     * 方法，并通过调用 fireChannelRead(msg) 方法将它转发给下一个 ChannelInboundHandler。之所以需要调用 retain()
     * 方法，是因为调用 channelRead() 方法完成之后，它将调用 FullHttpRequest 对象上的 release() 方法以释放它的资源。
     *
     * 如果客户端发送了 HTTP 1.1 的 HTTP 头信息 Expect: 100-continue，那么 HttpRequestHandler 将会发送一个 100
     * Continue 响应。在该 HTTP 头信息被设置之后，HttpRequestHandler 将会写回一个 HttpResponse 给客户端。这不是一
     * 个 FullHttpResponse，因为它只是响应的第一个部分。此外，这里也不会调用 writeAndFlush() 方法，在结束的时候才会
     * 调用。
     *
     * 如果不需要加密和压缩，那么可以通过将 index.html 的内容存储到 DefaultFileRegion 中来达到最佳效率。这将会利用零
     * 拷贝特性来进行内容的传输。为此，你可以检查一下，是否有 SslHandler 存在于在 ChannelPipeline 中。否则，你可以使
     * 用 ChunkedNioFile。
     *
     * HttpRequestHandler 将写一个 LastHttpContent 来标记响应的结束。如果没有请求 keep-alive，那么
     * HttpRequestHandler 将会添加一个 ChannelFutureListener 到最后一次写出动作的 ChannelFuture，并关闭该连接。
     * 在这里，你将调用 writeAndFlush() 方法以冲刷所有之前写入的消息。
     *
     * 这部分代码代表了聊天服务器的第一个部分，它管理纯粹的 HTTP 请求和响应，下面将处理传输实际聊天消息的 WebSocket 帧。
     *
     * 关于 WEBSOCKET 帧：
     * WebSocket 以帧的方式传输数据，每一帧代表消息的一部分。一个完整的消息可能会包含许多帧。
     *
     *
     *
     * 处理 WebSocket 帧
     *
     * 由 IETF 发布的 WebSocket RFC，定义了 6 种帧，Netty 为它们每种都提供了一个 POJO 实现。
     *
     * 如下列出了这些帧类型，并描述了它们的用法。
     * （1）BinaryWebSocketFrame：包含了二进制数据。
     * （2）TextWebSocketFrame：包含了文本数据。
     * （3）ContinuationWebSocketFrame：包含属于上一个 BinaryWebSocketFrame 或 TextWebSocketFrame 的文本数据或
     * 者二进制数据。
     * （4）CloseWebSocketFrame：表示一个 CLOSE 请求，包含一个关闭的状态码和关闭的原因。
     * （5）PingWebSocketFrame：请求传输一个 PongWebSocketFrame。
     * （6）PongWebSocketFrame：作为一个对于 PingWebSocketFrame 的响应被发送。
     *
     * 这里的聊天应用程序将使用下面几种帧类型：
     * （1）CloseWebSocketFrame
     * （2）PingWebSocketFrame
     * （3）PongWebSocketFrame
     * （4）TextWebSocketFrame
     *
     * TextWebSocketFrame 是这里唯一真正需要处理的帧类型。为了符合 WebSocket RFC，Netty 提供了
     * WebSocketServerProtocolHandler 来处理其他类型的帧。
     *
     * 以 TextWebSocketFrameHandler 为例，展示了用于处理 TextWebSocketFrame 的 ChannelInboundHandler，其还将在
     * 它的 ChannelGroup 中跟踪所有活动的 WebSocket 连接。
     *
     * TextWebSocketFrameHandler 只有一组非常少量的责任。当和新客户端的 WebSocket 握手成功完成之后，它将通过把通知消
     * 息写到 ChannelGroup 中的所有 Channel 来通知所有已经连接的客户端，然后它将把这个新 Channel 加入到该
     * ChannelGroup 中。
     *
     * 如果接收到了 TextWebSocketFrame 消息，TextWebSocketFrameHandler 将调用 TextWebSocketFrame 消息上的
     * retain() 方法，并使用 writeAndFlush() 方法来将它传输给 ChannelGroup，以便所有已经连接的 WebSocket Channel
     * 都将接收到它。
     *
     * 和之前一样，对于 retain() 方法的调用是必需的，因为当 channelRead0() 方法返回时，TextWebSocketFrame 的引用计
     * 数将会被减少。由于所有的操作都是异步的，因此，writeAndFlush() 方法可能会在 channelRead0() 方法返回之后完成，
     * 而且它绝对不能访问一个已经失效的引用。
     *
     * 因为 Netty 在内部处理了大部分剩下的功能，所以现在剩下唯一需要做的事情就是为每个新创建的 Channel 初始化其
     * ChannelPipeline。为此，需要一个 ChannelInitializer。
     *
     *
     *
     * 初始化 ChannelPipeline
     *
     * 为了将 ChannelHandler 安装到 ChannelPipeline 中，扩展了 ChannelInitializer，并实现了 initChannel() 方法。
     *
     * 以 ChatServerInitializer 为例，展示了由此生成的 ChatServerInitializer 的代码。
     *
     * 对于 initChannel() 方法的调用，通过安装所有必需的 ChannelHandler 来设置该新注册的 Channel 的
     * ChannelPipeline。
     *
     * 这些 ChannelHandler 以及它们各自的职责如下：
     * （1）HttpServerCodec：
     * 将字节解码为 HttpRequest、HttpContent 和 LastHttpContent。并将 HttpRequest、HttpContent 和 LastHttpContent
     * 编码为字节。
     *
     * （2）ChunkedWriteHandler：
     * 写入一个文件的内容。
     *
     * （3）HttpObjectAggregator：
     * 将一个 HttpMessage 和跟随它的多个 HttpContent 聚合为单个 FullHttpRequest 或者 FullHttpResponse（取决于它
     * 是被用来处理请求还是响应）。安装了这个之后，ChannelPipeline 中的下一个 ChannelHandler 将只会收到完整的 HTTP
     * 请求或响应。
     *
     * （4）HttpRequestHandler：
     * 处理 FullHttpRequest（那些不发送到 /ws URI 的请求）。
     *
     * （5）WebSocketServerProtocolHandler：
     * 按照 WebSocket 规范的要求，处理 WebSocket 升级握手、PingWebSocketFrame、PongWebSocketFrame
     * 和 CloseWebSocketFrame。
     *
     * （6）TextWebSocketFrameHandler：
     * 处理 TextWebSocketFrame 和握手完成事件。
     *
     * Netty 的 WebSocketServerProtocolHandler 处理了所有委托管理的 WebSocket 帧类型以及升级握手本身。如果握手成功，
     * 那么所需的 ChannelHandler 将会被添加到 ChannelPipeline 中，而那些不再需要的 ChannelHandler 则将会被移除。
     *
     * WebSocket 协议升级之前的 ChannelPipeline 的状态如下：
     * （1）HttpRequestDecoder
     * （2）HttpResponseEncoder
     * （3）HttpObjectAggregator
     * （4）HttpRequestHandler
     * （5）WebSocketServerProtocolHandler
     * （6）TextWebSocketFrameHandler
     *
     * 当 WebSocket 协议升级完成之后，WebSocketServerProtocolHandler 将会把 HttpRequestDecoder 替换为
     * WebSocketFrameDecoder，把 HttpResponseEncoder 替换为 WebSocketFrameEncoder。为了性能最大化，它将移除任何
     * 不再被 WebSocket 连接所需要的 ChannelHandler，这也包括了 HttpObjectAggregator 和 HttpRequestHandler。
     *
     * 如下展示了这些操作完成之后的 ChannelPipeline。需要注意的是，Netty 目前支持 4 个版本的 WebSocket 协议，它们每个
     * 都具有自己的实现类。Netty 将会根据客户端（这里指浏览器）所支持的版本，自动地选择正确版本的 WebSocketFrameDecoder
     * 和 WebSocketFrameEncoder。
     * （1）WebSocket13FrameDecoder（实现自 WebSocketFrameDecoder）
     * （2）WebSocket13FrameEncoder（实现自 WebSocket13FrameEncoder）
     * （3）WebSocketServerProtocolHandler
     * （4）TextWebSocketFrameHandler
     *
     * PS：这里假设使用了 13 版的 WebSocket 协议。
     *
     *
     *
     * 引导
     *
     * 最后的一部分是引导该服务器，并安装 ChatServerInitializer 的代码。以 ChatServer 为例，这也就完成了该应用程序本身。
     */
    public static void main(String[] args) {

    }

}
