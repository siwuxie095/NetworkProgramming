package com.siwuxie095.network.chapter12th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-12-01 07:48:57
 */
public class Main {

    /**
     * 测试该应用程序
     *
     * 运行 example4th 中 ChatServer 的 main 方法，并指定端口为 8000。
     *
     * 打开浏览器，然后打开多个页面，全部都输入 http://localhost:8000/。连接后，即可正常在多个客户端之间进行
     * 聊天。
     *
     *
     *
     * 如何进行加密
     *
     * 在真实世界的场景中，你将很快就会被要求向该服务器添加加密。使用 Netty，这不过是将一个 SslHandler 添加到
     * ChannelPipeline 中，并配置它的问题。
     *
     * 以 SecureChatServerInitializer 为例，展示了如何通过扩展 example4th 中的 ChatServerInitializer
     * 来创建一个 SecureChatServerInitializer 以完成这个需求。
     *
     * 最后一步是调整 ChatServer 以使用 SecureChatServerInitializer，以便在 ChannelPipeline 中安装
     * SslHandler。
     *
     * 以 SecureChatServer 为例，这就是为所有的通信启用 SSL/TLS 加密需要做的全部。
     *
     * 和上面的测试方法一样，配置好端口后，运行 SecureChatServer 的 main 方法。
     *
     * 唯一不同的是，这里需要使用 HTTPS 进行访问：https://localhost:8000/。
     *
     * PS：其实还有另外一处不同之处，打开页面后，前者显示 ws://localhost:8000/ws，
     * 后者则显示 wss://localhost:8000/ws。
     */
    public static void main(String[] args) {

    }

}
