package com.siwuxie095.network.chapter10th.example3rd;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 扩展 ReplayingDecoder<Void> 以将字节解码为消息
 *
 * @author Jiajing Li
 * @date 2020-11-23 08:24:39
 */
@SuppressWarnings("all")
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {

    /**
     * 传入的 ByteBuf 是 ReplayingDecoderByteBuf
     */
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 从入站 ByteBuf 中读取一个 int，并将其添加到解码消息的 List 中
        out.add(in.readInt());
    }
}
