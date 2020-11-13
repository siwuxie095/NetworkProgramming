package com.siwuxie095.network.chapter5th.example7th;

/**
 * @author Jiajing Li
 * @date 2020-11-13 07:57:59
 */
public class Main {

    /**
     * ByteBuf 分配
     *
     * 下面将描述管理 ByteBuf 实例的不同方式。
     *
     *
     *
     * 按需分配：ByteBufAllocator 接口
     *
     * 为了降低分配和释放内存的开销，Netty 通过 ByteBufAllocator 接口实现了（ByteBuf 的）池化，它可以用来分配
     * 任意类型的 ByteBuf 实例。使用池化是特定于应用程序的决定，其并不会以任何方式改变 ByteBuf API（的语义）。
     *
     * 如下是 ByteBufAllocator 提供的一些操作：
     *
     * （1）返回一个基于堆或者直接内存存储的 ByteBuf。
     * 1）buffer()
     * 2）buffer(int initialCapacity)
     * 3）buffer(int initialCapacity, int maxCapacity)
     *
     * PS：默认地，当所运行的环境具有 sun.misc.Unsafe 支持时，返回基于直接内存存储的 ByteBuf，否则返回基于堆内
     * 存存储的 ByteBuf；当指定使用 PreferHeapByteBufAllocator 时，则只会返回基于堆内存存储的 ByteBuf。
     *
     * （2）返回一个基于堆内存存储的 ByteBuf。
     * 1）heapBuffer()
     * 2）heapBuffer(int initialCapacity)
     * 3）heapBuffer(int initialCapacity, int maxCapacity)
     *
     * （3）返回一个基于直接内存存储的 ByteBuf。
     * 1）directBuffer()
     * 2）directBuffer(int initialCapacity)
     * 3）directBuffer(int initialCapacity, int maxCapacity)
     *
     * （4）返回一个可以通过添加最大到指定数目的基于堆的或者直接内存存储的缓冲区来扩展的 CompositeByteBuf。
     * 1）compositeBuffer()
     * 2）compositeBuffer(int maxNumComponents)
     * 3）compositeDirectBuffer()
     * 4）compositeDirectBuffer(int maxNumComponents)
     * 5）compositeHeapBuffer()
     * 6）compositeHeapBuffer(int maxNumComponents)
     *
     * （5）返回一个用于套接字的 I/O 操 作的 ByteBuf
     * 1）ioBuffer()
     *
     * 可以通过 Channel（每个都可以有一个不同的 ByteBufAllocator 实例）或者绑定到 ChannelHandler 的
     * ChannelHandlerContext 获取一个到 ByteBufAllocator 的引用。
     *
     * 以 ByteBufAllocatorExamples 中的 obtainingByteBufAllocatorReference() 方法为例，展示了获取一个到
     * ByteBufAllocator 的引用。
     *
     * Netty提供了两种 ByteBufAllocator 的实现：
     * （1）PooledByteBufAllocator；
     * （2）UnpooledByteBufAllocator。
     *
     * 前者池化了 ByteBuf 的实例以提高性能并最大限度地减少内存碎片。此实现使用了一种称为 jemalloc 的已被大量现代
     * 操作系统所采用的高效方法来分配内存。后者的实现不池化 ByteBuf 实例，并且在每次它被调用时都会返回一个新的实例。
     *
     * PS：关于 jemalloc 可参考 https://people.freebsd.org/~jasone/jemalloc/bsdcan2006/jemalloc.pdf
     *
     * 虽然 Netty 默认使用了 PooledByteBufAllocator，但这可以很容易地通过 ChannelConfig API 或者在引导你的
     * 应用程序时指定一个不同的分配器来更改。
     *
     * PS：这里指 Netty 4.1.x，Netty 4.0.x 默认使用的是 UnpooledByteBufAllocator。
     *
     *
     *
     * Unpooled 缓冲区
     *
     * 可能某些情况下，你未能获取一个到 ByteBufAllocator 的引用。对于这种情况，Netty 提供了一个简单的称为
     * Unpooled 的工具类，它提供了静态的辅助方法来创建未池化的 ByteBuf 实例。
     *
     * 如下列举了这些中最重要的方法：
     * （1）返回一个未池化的基于堆内存存储的 ByteBuf。
     * 1）buffer()
     * 2）buffer(int initialCapacity)
     * 3）buffer(int initialCapacity, int maxCapacity)
     *
     * （2）返回一个未池化的基于直接内存存储的 ByteBuf。
     * 1）directBuffer()
     * 2）directBuffer(int initialCapacity)
     * 3）directBuffer(int initialCapacity, int maxCapacity)
     *
     * （3）返回一个包装了给定数据的 ByteBuf。
     * 1）wrappedBuffer()
     *
     * （4）返回一个复制了给定数据的 ByteBuf。
     * 1）copiedBuffer()
     *
     * Unpooled 类还使得 ByteBuf 同样可用于那些并不需要 Netty 的其他组件的非网络项目，使得其能得益于高性能的
     * 可扩展的缓冲区 API。
     *
     *
     *
     * ByteBufUtil 类
     *
     * ByteBufUtil 提供了用于操作 ByteBuf 的静态的辅助方法。因为这个 API 是通用的，并且和池化无关，所以这些方法
     * 已然在分配类的外部实现。
     *
     * 这些静态方法中最有价值的可能就是 hexdump() 方法，它以十六进制的表示形式打印 ByteBuf 的内容。这在各种情况
     * 下都很有用，例如，出于调试的目的记录 ByteBuf 的内容。十六进制的表示通常会提供一个比字节值的直接表示形式更加
     * 有用的日志条目，此外，十六进制的版本还可以很容易地转换回实际的字节表示。
     *
     * 另一个有用的方法是 boolean equals(ByteBuf, ByteBuf)，它被用来判断两个 ByteBuf 实例的相等性。如果实现
     * 了自己的 ByteBuf 子类，你可能会发现 ByteBufUtil 的其他有用方法。
     */
    public static void main(String[] args) {

    }

}
