package com.siwuxie095.network.chapter11th.example7th;

import io.netty.channel.*;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import java.io.Serializable;

/**
 * 使用 JBoss Marshalling
 *
 * @author Jiajing Li
 * @date 2020-11-29 17:06:50
 */
@SuppressWarnings("all")
public class MarshallingInitializer extends ChannelInitializer<Channel> {

    private final MarshallerProvider marshallerProvider;
    private final UnmarshallerProvider unmarshallerProvider;

    public MarshallingInitializer(
            UnmarshallerProvider unmarshallerProvider,
            MarshallerProvider marshallerProvider) {
        this.marshallerProvider = marshallerProvider;
        this.unmarshallerProvider = unmarshallerProvider;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 添加 MarshallingDecoder 以将 ByteBuf 转换为 POJO
        pipeline.addLast(new MarshallingDecoder(unmarshallerProvider));
        // 添加 MarshallingEncoder 以将POJO 转换为 ByteBuf
        pipeline.addLast(new MarshallingEncoder(marshallerProvider));
        // 添加 ObjectHandler，以处理普通的实现了 Serializable 接口的 POJO
        pipeline.addLast(new ObjectHandler());
    }


    public static final class ObjectHandler extends SimpleChannelInboundHandler<Serializable> {

        @Override
        public void channelRead0(
                ChannelHandlerContext channelHandlerContext,
                Serializable serializable) throws Exception {
            // Do something
        }

    }

}

