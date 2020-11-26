package com.siwuxie095.network.chapter10th.example5th;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Jiajing Li
 * @date 2020-11-25 22:23:56
 */
@SuppressWarnings("all")
public class CharToByteEncoder extends MessageToByteEncoder<Character> {

    @Override
    public void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        // 将 Character 解码为 char，并将其写入到出站 ByteBuf 中
        out.writeChar(msg);
    }

}
