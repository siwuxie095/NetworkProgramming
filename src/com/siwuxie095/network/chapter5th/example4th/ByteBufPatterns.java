package com.siwuxie095.network.chapter5th.example4th;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * @author Jiajing Li
 * @date 2020-11-12 07:56:45
 */
@SuppressWarnings("all")
public class ByteBufPatterns {

    private static final ByteBuf BYTE_BUF_FROM_SOMEWHERE = Unpooled.buffer(1024);

    /**
     * 支撑数组
     */
    public static void heapBuffer() {
        // get reference form somewhere
        ByteBuf heapBuf = BYTE_BUF_FROM_SOMEWHERE;
        // 检查 ByteBuf 是否有一个支撑数组
        if (heapBuf.hasArray()) {
            // 如果有，则获取对该数组的引用
            byte[] array = heapBuf.array();
            // 计算第一个字节的偏移量
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            // 获得可读字节数
            int length = heapBuf.readableBytes();
            // 使用数组、偏移量和长度作为参数调用你的方法
            handleArray(array, offset, length);
        }
    }

    /**
     * 访问直接缓冲区的数据
     */
    public static void directBuffer() {
        // get reference form somewhere
        ByteBuf directBuf = BYTE_BUF_FROM_SOMEWHERE;
        // 检查 ByteBuf 是否由数组支撑。如果不是，则这是一个直接缓冲区
        if (!directBuf.hasArray()) {
            // 获取可读字节数
            int length = directBuf.readableBytes();
            // 分配一个新的数组来保存具有该长度的字节数据
            byte[] array = new byte[length];
            // 将字节复制到该数组
            directBuf.getBytes(directBuf.readerIndex(), array);
            // 使用数组、偏移量和长度作为参数调用你的方法
            handleArray(array, 0, length);
        }
    }

    /**
     * 使用 ByteBuffer 的复合缓冲区模式
     */
    public static void byteBufferComposite(ByteBuffer header, ByteBuffer body) {
        // Use an array to hold the message parts
        ByteBuffer[] message =  new ByteBuffer[]{ header, body };

        // Create a new ByteBuffer and use copy to merge the header and body
        ByteBuffer message2 =
                ByteBuffer.allocate(header.remaining() + body.remaining());
        message2.put(header);
        message2.put(body);
        message2.flip();
    }


    /**
     * 使用 CompositeByteBuf 的复合缓冲区模式
     */
    public static void byteBufComposite() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        // can be backing or direct
        ByteBuf headerBuf = BYTE_BUF_FROM_SOMEWHERE;
        // can be backing or direct
        ByteBuf bodyBuf = BYTE_BUF_FROM_SOMEWHERE;
        //将 ByteBuf 实例追加到 CompositeByteBuf
        messageBuf.addComponents(headerBuf, bodyBuf);
        // ...
        // 删除位于索引位置为 0（第一个组件）的 ByteBuf
        // remove the header
        messageBuf.removeComponent(0);
        // 循环遍历所有的 ByteBuf 实例
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
    }

    /**
     * 访问 CompositeByteBuf 中的数据
     */
    public static void byteBufCompositeArray() {
        CompositeByteBuf compBuf = Unpooled.compositeBuffer();
        // 获得可读字节数
        int length = compBuf.readableBytes();
        // 分配一个具有可读字节数长度的新数组
        byte[] array = new byte[length];
        // 将字节读到该数组中
        compBuf.getBytes(compBuf.readerIndex(), array);
        // 使用偏移量和长度作为参数使用该数组
        handleArray(array, 0, array.length);
    }

    private static void handleArray(byte[] array, int offset, int len) {}

}

