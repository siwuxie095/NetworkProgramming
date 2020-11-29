package com.siwuxie095.network.chapter11th.example6th;

/**
 * @author Jiajing Li
 * @date 2020-11-29 16:27:02
 */
public class Main {

    /**
     * 写大型数据
     *
     * 因为网络饱和的可能性，如何在异步框架中高效地写大块的数据是一个特殊的问题。由于写操作是非阻塞的，所以即使没有写出所有的
     * 数据，写操作也会在完成时返回并通知 ChannelFuture。当这种情况发生时，如果仍然不停地写入，就有内存耗尽的风险。所以在写
     * 大型数据时，需要准备好处理到远程节点的连接是慢速连接的情况，这种情况会导致内存释放的延迟。
     *
     * 不妨考虑下将一个文件内容写出到网络的情况。Netty 拥有 NIO 的零拷贝特性，这种特性消除了将文件的内容从文件系统移动到网络
     * 栈的复制过程。所有的这一切都发生在 Netty 的核心中，所以应用程序所有需要做的就是使用一个 FileRegion 接口的实现，其在
     * Netty 的 API 文档中的定义是："通过支持零拷贝的文件传输的 Channel 来发送的文件区域"。
     *
     * 以 FileRegionWriteHandler 为例，展示了如何通过从 FileInputStream 创建一个 DefaultFileRegion，并将其写入
     * Channel（甚至可以利用 io.netty.channel.ChannelProgressivePromise 来实时获取传输的进度），从而利用零拷贝特性来
     * 传输一个文件的内容。
     *
     * 这个示例只适用于文件内容的直接传输，不包括应用程序对数据的任何处理。在需要将数据从文件系统复制到用户内存中时，可以使用
     * ChunkedWriteHandler，它支持异步写大型数据流，而又不会导致大量的内存消耗。
     *
     * 关键是 interface ChunkedInput<B>，其中类型参数 B 是 readChunk() 方法返回的类型。Netty 预置了该接口的 4 个实现，
     * 每个都代表了一个将由 ChunkedWriteHandler 处理的不定长度的数据流。如下：
     * （1）ChunkedFile：从文件中逐块获取数据，当你的平台不支持零拷贝或者你需要转换数据时使用。
     * （2）ChunkedNioFile：和 ChunkedFile 类似，只是它使用了 FileChannel。
     * （3）ChunkedStream：从 InputStream 中逐块传输内容。
     * （4）ChunkedNioStream：从ReadableByteChannel中逐块传输内容。
     *
     * 以 ChunkedWriteHandlerInitializer 为例，说明了 ChunkedStream 的用法，它是实践中最常用的实现。所示的类使用了一个
     * File 以及一个 SslContext 进行实例化。当 initChannel() 方法被调用时，它将使用所示的 ChannelHandler 链初始化该
     * Channel。
     *
     * 当 Channel 的状态变为活动（活跃）时，WriteStreamHandler 将会逐块地把来自文件中的数据作为 ChunkedStream 写入。数
     * 据在传输之前将会由 SslHandler 加密。
     *
     * PS：逐块输入，要使用你自己的 ChunkedInput 实现，请在 ChannelPipeline 中安装一个 ChunkedWriteHandler。
     *
     * 这里讨论了如何通过使用零拷贝特性来高效地传输文件，以及如何通过使用 ChunkedWriteHandler 来写大型数据而又不必冒着导致
     * OutOfMemoryError 的风险。
     */
    public static void main(String[] args) {

    }

}
