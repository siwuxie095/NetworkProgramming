package com.siwuxie095.network.chapter10th.example4th;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 扩展了 MessageToMessageEncoder
 *
 * @author Jiajing Li
 * @date 2020-11-25 07:43:40
 */
@SuppressWarnings("all")
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {

    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        // 将 Integer 转换为 String，并将其添加到 List 中
        out.add(String.valueOf(msg));
    }

}
