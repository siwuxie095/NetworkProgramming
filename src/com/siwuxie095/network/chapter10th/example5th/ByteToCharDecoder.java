package com.siwuxie095.network.chapter10th.example5th;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 扩展了 ByteToMessageDecoder
 *
 * @author Jiajing Li
 * @date 2020-11-25 22:21:21
 */
@SuppressWarnings("all")
public class ByteToCharDecoder extends ByteToMessageDecoder {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 2) {
            // 将一个或者多个 Character 对象添加到传出的 List 中
            out.add(in.readChar());
        }
    }
}
