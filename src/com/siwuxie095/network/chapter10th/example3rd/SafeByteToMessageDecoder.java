package com.siwuxie095.network.chapter10th.example3rd;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * 扩展 ByteToMessageDecoder 以将字节解码为消息
 *
 * @author Jiajing Li
 * @date 2020-11-23 08:27:59
 */
@SuppressWarnings("all")
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {

    private static final int MAX_FRAME_SIZE = 1024;

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readable = in.readableBytes();
        // 检查缓冲区中是否有超过 MAX_FRAME_SIZE 个字节
        if (readable > MAX_FRAME_SIZE) {
            // 跳过所有的可读字节，抛出 TooLongFrameException 并通知 ChannelHandler
            in.skipBytes(readable);
            throw new TooLongFrameException("Frame too big!");
        }
        // do something
        // ...
    }

}

