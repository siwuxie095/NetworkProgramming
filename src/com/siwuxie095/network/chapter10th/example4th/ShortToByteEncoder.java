package com.siwuxie095.network.chapter10th.example4th;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 扩展了 MessageToByteEncoder
 *
 * @author Jiajing Li
 * @date 2020-11-25 07:42:18
 */
@SuppressWarnings("all")
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {

    @Override
    public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        // 将 Short 写入 ByteBuf 中
        out.writeShort(msg);
    }

}
