package com.siwuxie095.network.chapter13th.example8th;

/**
 * @author Jiajing Li
 * @date 2020-12-03 20:55:15
 */
public class Main {

    /**
     * 运行 LogEventBroadcaster 和 LogEventMonitor
     *
     * 首先，需要启动 LogEventBroadcaster，这将通过 UDP 协议广播日志消息。
     *
     * 然后，在一个新窗口中，构建并且启动 LogEventMonitor 以接收和显示广播消息。
     *
     * 当看到 LogEventMonitor running 时，就知道它已经成功地启动了。如果有错误发生，则将会打印异常信息。
     *
     * 当任何新的日志事件被添加到该日志文件中时，该终端都会显示它们。消息的格式则是由 LogEventHandler 创
     * 建的。
     *
     * 可以根据需要启动任意多的监视器实例，它们每一个都将接收并显示相同的消息。
     */
    public static void main(String[] args) {

    }

}
