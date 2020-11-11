package com.siwuxie095.network.chapter5th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-11 21:22:38
 */
public class Main {

    /**
     * ByteBuf 是如何工作的
     *
     * 因为所有的网络通信都涉及字节序列的移动，所以高效易用的数据结构明显是必不可少的。Netty 的 ByteBuf 实现
     * 满足并超越了这些需求。下面来看看它是如何通过使用不同的索引来简化对它所包含的数据的访问的吧。
     *
     * ByteBuf 维护了两个不同的索引：一个用于读取，一个用于写入。当你从 ByteBuf 读取时， 它的 readerIndex
     * 将会被递增已经被读取的字节数。同样地，当你写入 ByteBuf 时，它的 writerIndex 也会被递增。
     *
     * 一个空 ByteBuf 的布局结构和状态 即 一个读索引和写索引都设置为 0 的 16 字节 ByteBuf。
     *
     * PS：readerIndex 和 writerIndex 的起始位置都为索引位置 0。
     *
     * 要了解这些索引两两之间的关系，请考虑一下，如果打算读取字节直到 readerIndex 达到和 writerIndex 同样的
     * 值时会发生什么。在那时，将会到达 "可以读取的" 数据的末尾。就如同试图读取超出数组末尾的数据一样，试图读取
     * 超出该点的数据将会触发一个 IndexOutOfBoundsException。
     *
     * 名称以 read 或者 write 开头的 ByteBuf 方法，将会推进其对应的索引，而名称以 set 或 者 get 开头的操作
     * 则不会。后者将在作为一个参数传入的一个相对索引上执行操作。
     *
     * 可以指定 ByteBuf 的最大容量。试图移动写索引（即 writerIndex）超过这个值将会触发一个异常（默认的限制是
     * Integer.MAX_VALUE）。也就是说，用户直接或者间接使用 capacity(int) 或者 ensureWritable(int) 方法
     * 来增加超过该最大容量时会抛出异常。
     *
     * PS：ByteBuf 的最大容量定义在 AbstractByteBuf 中的变量 maxCapacity，AbstractByteBuf 抽象类继承自
     * ByteBuf 类。
     */
    public static void main(String[] args) {

    }

}
