package com.siwuxie095.network.chapter14th.example13th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 12:01:27
 */
public class Main {

    /**
     * 控制 SslHandler
     *
     * Netty 的 SslHandler 类是 Firebase 如何使用 Netty 来对它的网络通信进行细粒度控制的一个例子。
     * 当传统的 Web 技术栈使用 Apache 或者 Nginx 之类的 HTTP 服务器来将请求传递给应用程序时，传入的
     * SSL 请求在被应用程序的代码接收到的时候就已经被解码了。在多租户的架构体系中，很难将部分的加密流量
     * 分配给使用了某个特定服务的应用程序的租户。这很复杂，因为事实上多个应用程序可能使用了相同的加密
     * Channel 来和 Firebase 通信（例如，用户可能在不同的标签页中打开了两个 Firebase 应用程序）。为
     * 了解决这个问题，Firebase 需要在 SSL 请求被解码之前对它们拥有足够的控制来处理它们。
     *
     * Firebase 基于带宽向客户进行收费。然而，对于某个消息来说，在 SSL 解密被执行之前，要收取费用的账
     * 户通常是不知道的，因为它被包含在加密了的有效负载中。Netty 使得 Firebase 可以在 ChannelPipeline
     * 中的多个位置对流量进行拦截，因此对于字节数的统计可以从字节刚被从套接字读取出来时便立即开始。在消
     * 息被解密并且被 Firebase 的服务器端逻辑处理之后，字节计数便可以被分配给对应的账户。在构建这项功
     * 能时，Netty 在协议栈的每一层上，都提供了对于处理网络通信的控制，并且也使得非常精确的计费、限流以
     * 及速率限制成为了可能，所有的这一切都对业务具有显著的影响。
     *
     * Netty 使得通过少量的 Scala 代码便可以拦截所有的入站消息和出站消息并且统计字节数成为了可能。
     *
     * 另外：部分摘要代码，可参考 PDF。
     */
    public static void main(String[] args) {

    }

}
