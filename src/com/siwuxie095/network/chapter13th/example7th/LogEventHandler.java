package com.siwuxie095.network.chapter13th.example7th;

import com.siwuxie095.network.chapter13th.example5th.LogEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 扩展 SimpleChannelInboundHandler 以处理 LogEvent 消息
 *
 * @author Jiajing Li
 * @date 2020-12-03 08:21:25
 */
@SuppressWarnings("all")
public class LogEventHandler
        extends SimpleChannelInboundHandler<LogEvent> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        // 当异常发生时，打印栈跟踪信息，并关闭对应的 Channel
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             LogEvent event) throws Exception {
        // 创建 StringBuilder，并且构建输出的字符串
        StringBuilder builder = new StringBuilder();
        builder.append(event.getReceivedTimestamp());
        builder.append(" [");
        builder.append(event.getSource().toString());
        builder.append("] [");
        builder.append(event.getLogfile());
        builder.append("] : ");
        builder.append(event.getMsg());
        // 打印 LogEvent 的数据
        System.out.println(builder.toString());
    }
}

