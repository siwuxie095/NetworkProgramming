package com.siwuxie095.network.chapter5th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-12 07:46:50
 */
public class Main {

    /**
     * ByteBuf 的使用模式
     *
     * 在使用 Netty 时，你将遇到几种常见的围绕 ByteBuf 而构建的使用模式。在研究它们时，不妨带着 ByteBuf 的数据
     * 结构进行思考，即 一个由不同的索引分别控制读访问和写访问的字节数组。
     *
     * 一共有三种 ByteBuf 模式，如下：
     * （1）堆缓冲区
     * （2）直接缓冲区
     * （3）复合缓冲区
     *
     *
     *
     * 堆缓冲区
     *
     * 最常用的 ByteBuf 模式是将数据存储在 JVM 的堆空间中。这种模式被称为支撑数组（backing array），它能在没有
     * 使用池化的情况下提供快速的分配和释放。
     *
     * 以 ByteBufPatterns 中的 heapBuffer() 方法为例，这种方式非常适合于有遗留的数据需要处理的情况。
     *
     * 注意：当 hasArray() 方法返回 false 时，尝试访问支撑数组将触发一个 UnsupportedOperationException。这
     * 个模式类似于 JDK 的 ByteBuffer 的用法。
     *
     *
     *
     * 直接缓冲区
     *
     * 直接缓冲区是另外一种 ByteBuf 模式。虽然期望用于对象创建的内存分配永远都来自于堆中，但这并不是必须的。NIO
     * 在 JDK 1.4 中引入的 ByteBuffer 类允许 JVM 实现通过本地调用来分配内存。这主要是为了避免在每次调用本地
     * I/O 操作之前（或者之后）将缓冲区的内容复制到一个中间缓冲区（或者从中间缓冲区把内容复制到缓冲区）。
     *
     * ByteBuffer 的 Javadoc 明确指出：直接缓冲区的内容将驻留在常规的会被垃圾回收的堆之外。这也就解释了为何直接
     * 缓冲区对于网络数据传输是理想的选择。如果你的数据包含在一个在堆上分配的缓冲区中，那么事实上，在通过套接字发送
     * 它之前，JVM 将会在内部把你的缓冲区复制到一个直接缓冲区中。
     *
     * PS：ByteBuffer 的 Javadoc 可参见 https://docs.oracle.com/javase/8/docs/api/java/nio/ByteBuffer.html
     *
     * 直接缓冲区的主要缺点是：相对于基于堆的缓冲区，它们的分配和释放都较为昂贵。如果你正在处理遗留代码，你也可能会
     * 遇到另外一个缺点：因为数据不是在堆上，所以你不得不进行一次复制，具体以 ByteBufPatterns 的 directBuffer()
     * 方法为例。
     *
     *
     *
     * 复合缓冲区
     *
     * 第三种也是最后一种模式使用的是复合缓冲区，它为多个 ByteBuf 提供一个聚合视图。在这里你可以根据需要添加或者
     * 删除 ByteBuf 实例，这是一个 JDK 的 ByteBuffer 实现完全缺失的特性。
     *
     * Netty 通过一个 ByteBuf 的子类 CompositeByteBuf 实现了这个模式，它提供了一个将多个缓冲区表示为单个合并
     * 缓冲区的虚拟表示。
     *
     * 警告：CompositeByteBuf 中的 ByteBuf 实例可能同时包含直接内存分配和非直接内存分配。如果其中只有一个实例，
     * 那么对 CompositeByteBuf 上的 hasArray() 方法的调用将返回该组件上的 hasArray() 方法的值，否则它将返回
     * false。
     *
     * 为了举例说明，不妨考虑一下一个由两部分 —— 头部和主体 —— 组成的将通过 HTTP 协议传输的消息。这两部分由应用
     * 程序的不同模块产生，将会在消息被发送的时候组装。该应用程序可以选择为多个消息重用相同的消息主体。当这种情况
     * 发生时，对于每个消息都将会创建一个新的头部。
     *
     * 因为不想为每个消息都重新分配这两个缓冲区，所以使用 CompositeByteBuf 是一个完美的选择。它在消除了没必要的
     * 复制的同时，暴露了通用的 ByteBuf API。
     *
     * 以 ByteBufPatterns 的 byteBufferComposite() 方法为例，展示了如何通过使用 JDK 的 ByteBuffer 来实现
     * 这一需求。即 创建了一个包含两个 ByteBuffer 的数组用来保存这些消息组件，同时创建了第三个 ByteBuffer 用来
     * 保存所有这些数据的副本。其中有分配和复制操作，以及伴随着对数组管理的需要，使得这个版本的实现效率低下而且笨拙。
     *
     * 以 ByteBufPatterns 的 byteBufComposite() 方法为例，展示了一个使用了 CompositeByteBuf 的版本。
     *
     * CompositeByteBuf 可能不支持访问其支撑数组，因此访问 CompositeByteBuf 中的数据类似于（访问）直接缓冲区
     * 的模式，以 ByteBufPatterns 的 byteBufCompositeArray() 方法为例。
     *
     * 需要注意的是，Netty 使用了 CompositeByteBuf 来优化套接字的 I/O 操作，尽可能地消除了由 JDK 的缓冲区实现
     * 所导致的性能以及内存使用率的惩罚。这种优化发生在 Netty 的核心代码中，因此不会被暴露出来，但是你应该知道它所
     * 带来的影响。
     *
     * Netty 的这种优化这尤其适用于 JDK 所使用的一种称为分散/收集 I/O（Scatter/Gather I/O）的技术，它的定义为
     * 一种输入和输出的方法，其中，单个系统调用从单个数据流写到一组缓冲区中，或者，从单个数据源读到一组缓冲区中。
     *
     * PS：除了从 ByteBuf 继承的方法，CompositeByteBuf 提供了大量的附加功能。请参考 Netty 的 Javadoc 以获得
     * 该 API 的完整列表。
     */
    public static void main(String[] args) {

    }

}
