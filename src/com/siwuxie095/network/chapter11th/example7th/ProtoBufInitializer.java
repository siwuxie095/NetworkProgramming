package com.siwuxie095.network.chapter11th.example7th;

import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * 使用 protobuf
 *
 * @author Jiajing Li
 * @date 2020-11-29 17:19:42
 */
@SuppressWarnings("all")
public class ProtoBufInitializer extends ChannelInitializer<Channel> {

    private final MessageLite lite;

    public ProtoBufInitializer(MessageLite lite) {
        this.lite = lite;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加 ProtobufVarint32FrameDecoder 以分隔帧
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // 添加 ProtobufEncoder 以处理消息的编码
        // PS：还需要在当前的 ProtobufEncoder 之前添加一个相应的 ProtobufVarint32LengthFieldPrepender 以编码进帧长度信息
        pipeline.addLast(new ProtobufEncoder());
        // 添加 ProtobufDecoder 以解码消息
        pipeline.addLast(new ProtobufDecoder(lite));
        // 添加 ObjectHandler 以处理解码消息
        pipeline.addLast(new ObjectHandler());
    }


    public static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {

        @Override
        public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            // Do something with the object
        }

    }

}

