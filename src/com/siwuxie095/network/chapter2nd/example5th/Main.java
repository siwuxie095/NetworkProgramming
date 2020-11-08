package com.siwuxie095.network.chapter2nd.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-08 17:24:14
 */
public class Main {

    /**
     * 运行 Echo 服务器和客户端
     *
     * （1）先运行 example3rd 中的 EchoServer 服务器，需要配置端口为 8000。
     * （2）再运行 example4th 中的 EchoClient 客户端，需要配置主机和端口分别为 127.0.0.1 和 8000。
     *
     *
     * 每次运行客户端，在服务器的控制台中会看到如下内容：
     *
     * Server received: Netty rocks!
     *
     * 同时会在客户端的控制台中会看到如下内容：
     *
     * Client received: Netty rocks!
     *
     *
     * 下面是发生的事：
     * （1）一旦客户端建立连接，它就发送它的消息 "Netty rocks!"；
     * （2）服务器报告接收到的消息，并将其回送给客户端；
     * （3）客户端报告返回的消息并退出。
     *
     * 上面看到的都是预期的行为，下面看看故障是如何被处理的。现在停止服务器，然后再次启动客户端。
     *
     * 发生了什么？客户端试图连接服务器，其预期运行在 127.0.0.1:8000 上。但是连接失败了（和预期的一样），因为服务器
     * 在这之前就已经停止了，所以在客户端导致了一个 java.net.ConnectException。这个异常触发了 EchoClientHandler
     * 的 exceptionCaught() 方法，打印出了栈跟踪并关闭了 Channel。
     */
    public static void main(String[] args) {

    }

}
