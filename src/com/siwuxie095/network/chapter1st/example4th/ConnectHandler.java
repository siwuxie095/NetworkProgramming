package com.siwuxie095.network.chapter1st.example4th;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 被回调触发的 ChannelHandler
 *
 * @author Jiajing Li
 * @date 2020-11-05 08:22:47
 */
@SuppressWarnings("all")
public class ConnectHandler extends ChannelInboundHandlerAdapter {

    //当一个新的连接已经被建立时，channelActive(ChannelHandlerContext)将会被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }
}
