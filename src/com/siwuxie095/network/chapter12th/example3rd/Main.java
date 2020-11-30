package com.siwuxie095.network.chapter12th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-30 07:59:16
 */
public class Main {

    /**
     * WebSocket 示例应用程序
     *
     * 为了让示例应用程序展示它的实时功能，这里将通过使用 WebSocket 协议来实现一个基于浏览器的聊天应用程序，就像你
     * 可能在 Facebook 的文本消息功能中见到过的那样。这里将通过使得多个用户之间可以同时进行相互通信，从而更进一步。
     *
     * 该应用程序的逻辑如下：
     * （1）客户端发送一个消息（客户端连接服务器，并且成为聊天的一部分）；
     * （2）该消息将被广播到所有其他连接的客户端（服务器为所有的客户端提供服务）。
     *
     * 这正如你可能会预期的一个聊天室应当的工作方式：所有的人都可以和其他的人聊天。在示例中，这里将只实现服务器端，
     * 而客户端则是通过 Web 页面访问该聊天室的浏览器。WebSocket 简化了编写这样的服务器的过程。
     */
    public static void main(String[] args) {

    }

}
