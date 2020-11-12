package com.siwuxie095.network.chapter5th.example5th;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author Jiajing Li
 * @date 2020-11-12 20:22:54
 */
@SuppressWarnings("all")
public class ByteBufOperations {

    private final static Random RANDOM = new Random();

    private static final ByteBuf BYTE_BUF_FROM_SOMEWHERE = Unpooled.buffer(1024);

    /**
     * 访问数据
     */
    public static void byteBufRelativeAccess() {
        // get reference form somewhere
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.getByte(i);
            System.out.println((char) b);
        }
    }

    /**
     * 读取所有数据
     */
    public static void readAllData() {
        // get reference form somewhere
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        while (buffer.isReadable()) {
            System.out.println(buffer.readByte());
        }
    }

    /**
     * 写数据
     */
    public static void write() {
        // Fills the writable bytes of a buffer with RANDOM integers.
        //get reference form somewhere
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        while (buffer.writableBytes() >= 4) {
            buffer.writeInt(RANDOM.nextInt());
        }
    }

    /**
     * 使用 ByteBufProcessor 来寻找 \r
     *
     * use {@link io.netty.buffer.ByteBufProcessor in Netty 4.0.x}
     */
    public static void byteProcessor() {
        // get reference form somewhere
        ByteBuf buffer = BYTE_BUF_FROM_SOMEWHERE;
        int index = buffer.forEachByte(ByteProcessor.FIND_CR);
    }

    /**
     * 对 ByteBuf 进行切片
     */
    public static void byteBufSlice() {
        Charset utf8 = Charset.forName("UTF-8");
        // 创建一个用于保存给定字符串的字节的 ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 创建该 ByteBuf 从索引 0 开始到索引 15 结束的一个新切片
        ByteBuf sliced = buf.slice(0, 15);
        // 将打印 "Netty in Action"
        System.out.println(sliced.toString(utf8));
        // 更新索引 0 处的字节
        buf.setByte(0, (byte) 'J');
        // 将会成功，因为数据是共享的，对其中一个所做的更改对另外一个也是可见的
        assert buf.getByte(0) == sliced.getByte(0);
    }


    /**
     * 复制一个 ByteBuf
     */
    public static void byteBufCopy() {
        Charset utf8 = Charset.forName("UTF-8");
        // 创建 ByteBuf 以保存所提供的字符串的字节
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 创建该 ByteBuf 从索引 0 开始到索引 15 结束的分段的副本
        ByteBuf copy = buf.copy(0, 15);
        // 将打印 "Netty in Action"
        System.out.println(copy.toString(utf8));
        // 更新索引 0 处的字节
        buf.setByte(0, (byte) 'J');
        // 将会成功，因为数据不是共享的
        assert buf.getByte(0) != copy.getByte(0);
    }

    /**
     * get() 和 set() 方法的用法
     */
    public static void byteBufSetGet() {
        Charset utf8 = Charset.forName("UTF-8");
        // 创建一个新的 ByteBuf 以保存给定字符串的字节
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 打印第一个字符 'N'
        System.out.println((char) buf.getByte(0));
        // 存储当前的 readerIndex 和 writerIndex
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        // 将索引 0 处的字节更新为字符 'B'
        buf.setByte(0, (byte) 'B');
        // 打印第一个字符，现在是 'B'
        System.out.println((char) buf.getByte(0));
        // 将会成功，因为这些操作并不会修改相应的索引
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }

    /**
     * ByteBuf 上的 read() 和 write() 操作
     */
    public static void byteBufWriteRead() {
        Charset utf8 = Charset.forName("UTF-8");
        // 创建一个新的 ByteBuf 以保存给定字符串的字节
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 打印第一个字符 'N'
        System.out.println((char) buf.readByte());
        // 存储当前的 readerIndex
        int readerIndex = buf.readerIndex();
        // 存储当前的 writerIndex
        int writerIndex = buf.writerIndex();
        // 将字符 '?' 追加到缓冲区
        buf.writeByte((byte) '?');
        assert readerIndex == buf.readerIndex();
        // 将会成功，因为 writeByte() 方法移动了 writerIndex
        assert writerIndex != buf.writerIndex();
    }

}
