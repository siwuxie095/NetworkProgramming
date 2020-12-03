package com.siwuxie095.network.chapter13th.example7th;

/**
 * @author Jiajing Li
 * @date 2020-12-03 08:03:58
 */
public class Main {

    /**
     * 编写监视器
     *
     * 这里的目标是将 netcat 替换为一个更加完整的事件消费者，称之为 LogEventMonitor。这个程序将：
     * （1）接收由 LogEventBroadcaster 广播的 UDP DatagramPacket。
     * （2）将它们解码为 LogEvent 消息。
     * （3）将 LogEvent 消息写出到 System.out。
     *
     * 以 LogEventDecoder 为例，展示了解码器。
     *
     * 以 LogEventMonitor 为例，展示了监视器。
     *
     * 以 LogEventHandler 为例，用于处理解码出来的 LogEvent 消息，这里只是纯粹的打印。
     *
     * 和之前一样，该逻辑由一组自定义的 ChannelHandler 实现。对于解码器来说，扩展自 MessageToMessageDecoder。
     *
     * LogEventMonitor 的 ChannelPipeline 的一个高级视图是这样，ChannelPipeline 中的第一个解码器
     * LogEventDecoder 负责将从远程节点传入的 DatagramPacket 解码为 LogEvent 消息（一个用于转换入站数据的
     * 任何 Netty 应用程序的典型设置）。
     *
     * 第二个 ChannelHandler（即 LogEventHandler）的工作是对第一个ChannelHandler（即 LogEventDecoder）
     * 所创建的 LogEvent 消息执行一些处理。在这个场景下，它只是简单地将它们写出到 System.out。在真实世界的应用
     * 程序中，你可能需要聚合来源于不同日志文件的事件，或者将它们发布到数据库中。
     *
     * LogEventHandler 将以一种简单易读的格式打印 LogEvent 消息，包括以下的各项：
     * （1）以毫秒为单位的被接收的时间戳；
     * （2）发送方的 InetSocketAddress，其由 IP 地址和端口组成；
     * （3）生成 LogEvent 消息的日志文件的绝对路径名；
     * （4）实际上的日志消息，其代表日志文件中的一行。
     *
     * 最后，在 LogEventMonitor 中将 LogEventDecoder 和 LogEventHandler 安装到 ChannelPipeline 中。
     */
    public static void main(String[] args) {

    }

}
