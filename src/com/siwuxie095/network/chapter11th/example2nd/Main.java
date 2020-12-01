package com.siwuxie095.network.chapter11th.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-27 07:59:50
 */
public class Main {

    /**
     * 通过 SSL/TLS 保护 Netty 应用程序
     *
     * 如今，数据隐私是一个非常值得关注的问题，作为开发人员，需要准备好应对它。至少，应该熟悉像 SSL 和 TLS 这样
     * 的安全协议，它们层叠在其他协议之上，用以实现数据安全。在访问安全网站时会遇到这些协议，但是它们也可用于其他
     * 不是基于 HTTP 的应用程序，如安全 SMTP（SMTPS）邮件服务器甚至是关系型数据库系统。
     *
     * PS：传输层安全（TLS）协议，1.2 版：http://tools.ietf.org/html/rfc5246
     *
     * 为了支持 SSL/TLS，Java 提供了 javax.net.ssl 包，它的 SSLContext 和 SSLEngine 类使得实现解密和加密
     * 相当简单直接。Netty 通过一个名为 SslHandler 的 ChannelHandler 实现利用了这个 API，其中 SslHandler
     * 在内部使用 SSLEngine 来完成实际的工作。
     *
     * 通过 SslHandler 进行解密和加密的数据流如下：
     * （1）SslHandler 拦截了加密的入站数据。
     * （2）SslHandler 对数据进行了解密，并且将它定向到入站端。
     * （3）出站数据被传递通过 SslHandler。
     * （4）SslHandler 对数据进行了加密，并且传递给出站端。
     *
     *
     * 关于 Netty 的 OpenSSL/SSLEngine 实现：
     * Netty 还提供了使用 OpenSSL 工具包（www.openssl.org）的 SSLEngine 实现。这个 OpenSslEngine 类提供
     * 了比 JDK 提供的 SSLEngine 实现更好的性能。
     *
     * 如果 OpenSSL 库可用，可以将 Netty 应用程序（客户端和服务器）配置为默认使用 OpenSslEngine。如果不可用，
     * Netty 将会回退到 JDK 实现。有关配置 OpenSSL 支持的详细说明，参见 Netty 文档：
     * https://netty.io/wiki/forked-tomcat-native.html#wikih2-1。
     *
     * 注意，无论你使用 JDK 的 SSLEngine 还是使用 Netty 的 OpenSslEngine，SSL API 和数据流都是一致的。
     *
     *
     * 以 SslChannelInitializer 为例，展示了如何使用 ChannelInitializer 来将 SslHandler 添加到
     * ChannelPipeline 中。ChannelInitializer 用于在 Channel 注册好时设置 ChannelPipeline。
     *
     * 在大多数情况下，SslHandler 将是 ChannelPipeline 中的第一个 ChannelHandler。 这确保了只有在所有其他
     * 的 ChannelHandler 将它们的逻辑应用到数据之后，才会进行加密。
     *
     * SslHandler 具有一些有用的方法，如下所示。例如，在握手阶段，两个节点将相互验证并且商定一种加密方式。你可
     * 以通过配置 SslHandler 来修改它的行为，或者在 SSL/TLS 握手一旦完成之后提供通知，握手阶段完成之后，所有
     * 的数据都将会被加密。SSL/TLS 握手将会被自动执行。
     *
     * （1）设置和获取超时时间，超时之后，握手 ChannelFuture 将会被通知失败。
     * 1）setHandshakeTimeout(long, TimeUnit)
     * 2）setHandshakeTimeoutMillis(long)
     * 3）getHandshakeTimeoutMillis()
     *
     * （2）设置和获取超时时间，超时之后，将会触发一个关闭通知并关闭连接。这也将会导致通知该 ChannelFuture 失败。
     * 1）setCloseNotifyTimeout(long, TimeUnit)
     * 2）setCloseNotifyTimeoutMillis(long)
     * 3）getCloseNotifyTimeoutMillis()
     *
     * （3）返回一个在握手完成后将会得到通知的 ChannelFuture。如果握手先前已经执行过了，则返回一个包含了先前的
     * 握手结果的 ChannelFuture。
     * 1）handshakeFuture()
     *
     * （4）发送 close_notify 以请求关闭并销毁底层的 SslEngine。
     * 1）close()
     * 2）close(ChannelPromise)
     * 3）close(ChannelHandlerContext, ChannelPromise)
     */
    public static void main(String[] args) {

    }

}
