package com.siwuxie095.network.chapter2nd.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-06 08:10:57
 */
public class Main {

    /**
     * Netty 客户端/服务器概述
     *
     * 通过即将编写的 Echo 客户端和服务器应用程序，能更加全面地理解 Netty 的 API。
     *
     * 这里的客户端/服务器，是指多个客户端同时连接到一台服务器。对于所能够支持的客户端数量，在理论上，仅受限
     * 于系统的可用资源（以及所使用的 JDK 版本可能会施加的限制）。
     *
     * Echo 客户端和服务器之间的交互是非常简单的。在客户端建立一个连接之后，它会向服务器发送一个或多个消息，
     * 反过来，服务器又会将每个消息回送给客户端。虽然它本身看起来好像用处不大，但它充分地体现了客户端/服务器
     * 系统中典型的请求-响应交互模式。
     */
    public static void main(String[] args) {

    }

}
