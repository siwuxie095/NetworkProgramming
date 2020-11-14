package com.siwuxie095.network.chapter5th.example8th;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Jiajing Li
 * @date 2020-11-14 14:57:18
 */
@SuppressWarnings("all")
public class ReferenceCountedExamples {

    private static final ByteBuf BYTE_BUF_FROM_SOMEWHERE = Unpooled.buffer(1024);

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    /**
     * 引用计数
     */
    public static void referenceCounting() {
        // get reference form somewhere
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        //从 Channel 获取 ByteBufAllocator
        ByteBufAllocator allocator = channel.alloc();
        // ...
        // 从 ByteBufAllocator 分配一个 ByteBuf
        ByteBuf buffer = allocator.directBuffer();
        // 检查引用计数是否为预期的 1
        assert buffer.refCnt() == 1;
        // ...
    }

    /**
     * 释放引用计数的对象
     */
    public static void releaseReferenceCountedObject() {
        // get reference form somewhere
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        // 减少到该对象的活动引用。当减少到 0 时，该对象被释放，并且该方法返回 true
        boolean released = buffer.release();
        // ...
    }


}
