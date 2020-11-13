package com.siwuxie095.network.chapter5th.example7th;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;

import static io.netty.channel.DummyChannelHandlerContext.DUMMY_INSTANCE;

/**
 * @author Jiajing Li
 * @date 2020-11-13 08:08:54
 */
@SuppressWarnings("all")
public class ByteBufAllocatorExamples {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE = DUMMY_INSTANCE;

    /**
     * 获取一个到 ByteBufAllocator 的引用
     */
    public static void obtainingByteBufAllocatorReference(){
        // get reference form somewhere
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        //从 Channel 获取一个到 ByteBufAllocator 的引用
        ByteBufAllocator allocator = channel.alloc();
        // ...
        // get reference form somewhere
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        // 从 ChannelHandlerContext 获取一个到 ByteBufAllocator 的引用
        ByteBufAllocator allocator2 = ctx.alloc();
        // ...
    }

}
