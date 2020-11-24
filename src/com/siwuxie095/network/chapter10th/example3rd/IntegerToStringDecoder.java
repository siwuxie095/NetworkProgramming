package com.siwuxie095.network.chapter10th.example3rd;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 扩展了 MessageToMessageDecoder<Integer>
 *
 * @author Jiajing Li
 * @date 2020-11-23 08:26:35
 */
@SuppressWarnings("all")
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {

    @Override
    public void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        // 将 Integer 消息转换为它的 String 表示，并将其添加到输出的 List 中
        out.add(String.valueOf(msg));
    }

}
