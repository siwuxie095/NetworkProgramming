package com.siwuxie095.network.chapter13th.example9th;

/**
 * @author Jiajing Li
 * @date 2020-12-03 21:00:45
 */
public class Main {

    /**
     * 小结
     *
     * 在这里，使用了 UDP 作为例子介绍了无连接协议。构建了一个示例应用程序，其将日志条目转换为 UDP 数据报并广播它们，
     * 随后这些被广播出去的消息将被订阅的监视器客户端所捕获。这里的实现使用了一个 POJO 来表示日志数据，并通过一个自定
     * 义的编码器来将这个消息格式转换为 Netty 的 DatagramPacket。这个例子说明了 Netty 的 UDP 应用程序可以很轻松
     * 地被开发和扩展用以支持专业化的用途。
     */
    public static void main(String[] args) {

    }

}
