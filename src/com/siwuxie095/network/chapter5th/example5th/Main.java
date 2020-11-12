package com.siwuxie095.network.chapter5th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-12 20:19:37
 */
public class Main {

    /**
     * 字节级操作
     *
     * ByteBuf 提供了许多超出基本读、写操作的方法用于修改它的数据。下面将会讨论这些中最重要的部分。
     *
     *
     *
     * 随机访问索引
     *
     * 如同在普通的 Java 字节数组中一样，ByteBuf 的索引是从零开始的：第一个字节的索引是 0，最后一个字节的索引
     * 总是 capacity() - 1。
     *
     * 以 ByteBufOperations 中的 byteBufRelativeAccess() 方法为例，表明对存储机制的封装使得遍历 ByteBuf
     * 的内容非常简单。
     *
     * 需要注意的是，使用那些需要一个索引值参数的方法（的其中）之一来访问数据既不会改变 readerIndex 也不会改变
     * writerIndex。如果有需要，也可以通过调用 readerIndex(index) 或者 writerIndex(index) 来手动移动这
     * 两者。
     *
     *
     *
     * 顺序访问索引
     *
     * 虽然 ByteBuf 同时具有读索引和写索引，但是 JDK 的 ByteBuffer 却只有一个索引，这也就是为什么必须调用
     * flip() 方法来在读模式和写模式之间进行切换的原因。
     *
     * ByteBuf 被它的两个索引划分成了三个区域（即 ByteBuf 的内部分段）：
     * （1）可丢弃字节：已经被读过的可被丢弃的字节。
     * （2）可读字节：尚未被读过的字节。
     * （3）可写字节：可以添加更多字节的空间。
     *
     *
     *
     * 可丢弃字节
     *
     * 可丢弃字节的分段包含了已经被读过的字节。通过调用 discardReadBytes() 方法，可以丢弃它们并回收空间。这个
     * 分段的初始大小为 0，存储在 readerIndex 中， 会随着 read 操作的执行而增加。
     *
     * PS：get* 操作不会移动 readerIndex。
     *
     * 调用 discardReadBytes() 方法后，可丢弃字节分段中的空间就变为可写字节的空间了。
     *
     * 注意，在调用 discardReadBytes() 之后，对可写分段的内容并没有任何的保证。因为只是移动了可以读取的字节
     * 以及 writerIndex，而没有对所有可写入的字节进行擦除写。
     *
     * 虽然你可能会倾向于频繁地调用 discardReadBytes() 方法以确保可写分段的最大化，但是请注意，这将极有可能会
     * 导致内存复制，因为可读字节必须被移动到缓冲区的开始位置。这里建议只在有真正需要的时候才这样做，例如，当内存
     * 非常宝贵的时候。
     *
     *
     *
     * 可读字节
     *
     * ByteBuf 的可读字节分段存储了实际数据。新分配的、包装的或者复制的缓冲区的默认的 readerIndex 值为 0。任
     * 何名称以 read 或者 skip 开头的操作都将检索或者跳过位于当前 readerIndex 的数据，并且将它增加已读字节数。
     *
     * 如果被调用的方法需要一个 ByteBuf 参数作为写入的目标，并且没有指定目标索引参数，那么该目标缓冲区的 writerIndex
     * 也将被增加。
     *
     * 如果尝试在缓冲区的可读字节数已经耗尽时从中读取数据，那么将会引发一个 IndexOutOfBoundsException。
     *
     * 以 ByteBufOperations 中的 readAllData() 方法为例，展示了如何读取所有可以读的字节。
     *
     *
     *
     * 可写字节
     *
     * 可写字节分段是指一个拥有未定义内容的、写入就绪的内存区域。新分配的缓冲区的 writerIndex 的默认值为 0。任
     * 何名称以 write 开头的操作都将从当前的 writerIndex 处开始写数据，并将它增加已经写入的字节数。如果写操作
     * 的目标也是 ByteBuf，并且没有指定源索引的值，则源缓冲区的 readerIndex 也同样会被增加相同的大小。这个调
     * 用如下所示：
     *
     * writeBytes(ByteBuf dest);
     *
     * 如果尝试往目标写入超过目标容量的数据，将会引发一个 IndexOutOfBoundException。
     *
     * PS：在往 ByteBuf 中写入数据时，其将首先确保目标 ByteBuf 具有足够的可写入空间来容纳当前要写入的数据，如
     * 果没有，则将检查当前的写索引以及最大容量是否可以在扩展后容纳该数据，可以则会分配并调整容量，否则就会抛出
     * 该异常。
     *
     * 以 ByteBufOperations 中的 write() 方法为例，展示了用随机整数值填充缓冲区，直到它空间不足为止。其中，
     * writeableBytes() 方法在这里被用来确定该缓冲区中是否还有足够的空间。
     *
     *
     *
     * 索引管理
     *
     * JDK 的 InputStream 定义了 mark(int readlimit) 和 reset() 方法，这些方法分别被用来将流中的当前位置
     * 标记为指定的值，以及将流重置到该位置。
     *
     * 同样，可以通过调用 markReaderIndex()、markWriterIndex()、resetWriterIndex() 和 resetReaderIndex()
     * 来标记和重置 ByteBuf 的 readerIndex 和 writerIndex。这些和 InputStream 上的调用类似，只是没有
     * readlimit 参数来指定标记什么时候失效。
     *
     * 也可以通过调用 readerIndex(int) 或者 writerIndex(int) 来将索引移动到指定位置。试图将任何一个索引设
     * 置到一个无效的位置都将导致一个 IndexOutOfBoundsException。
     *
     * 可以通过调用 clear() 方法来将 readerIndex 和 writerIndex 都设置为 0。注意，这并不会清除内存中的内容。
     *
     * 在 clear() 方法被调用之后，可丢弃字节空间和可读字节空间都将清零，可写字节空间此时占据全部。
     *
     * 调用 clear() 比调用 discardReadBytes() 轻量得多，因为它将只是重置索引而不会复制任何的内存。
     *
     *
     *
     * 查找操作
     *
     * 在 ByteBuf 中有多种可以用来确定指定值的索引的方法。最简单的是使用 indexOf() 方法。较复杂的查找可以通过
     * 那些需要一个 ByteBufProcessor 作为参数的方法达成。这个接口只定义了一个方法，如下：
     *
     * boolean process(byte value);
     *
     * 它将检查输入值是否是正在查找的值。
     *
     * PS：在 Netty 4.1.x 中，ByteBufProcessor 类已经废弃，请使用 io.netty.util.ByteProcessor。
     *
     * ByteBufProcessor 针对一些常见的值定义了许多便利的方法。假设你的应用程序需要和所谓的包含有以 NULL 结尾
     * 的内容的 Flash 套接字集成。可以调用如下方法：
     *
     * forEachByte(ByteBufProcessor.FIND_NUL);
     *
     * 该方法将简单高效地消费该 Flash 数据，因为在处理期间只会执行较少的边界检查。
     *
     * 以 ByteBufOperations 中的 byteProcessor() 方法为例，展示了一个查找回车符（\r）。
     *
     * PS：有关 Flash 套接字的讨论可参考Flash Action Script 3.0 Developer's Guide 中 Networking and
     * Communication 部分里的 Sockets 页面，如下：
     * https://help.adobe.com/en_US/as3/dev/WSb2ba3b1aad8a27b0-181c51321220efd9d1c-8000.html
     *
     *
     *
     * 派生缓冲区
     *
     * 派生缓冲区为 ByteBuf 提供了以专门的方式来呈现其内容的视图。这类视图是通过以下方法被创建的：
     * （1）duplicate();
     * （2）slice();
     * （3）slice(int, int);
     * （4）Unpooled.unmodifiableBuffer(...);
     * （5）order(ByteOrder);
     * （6）readSlice(int);
     *
     * 每个这些方法都将返回一个新的 ByteBuf 实例，它具有自己的读索引、写索引和标记索引。其内部存储和 JDK 的
     * ByteBuffer 一样也是共享的。这使得派生缓冲区的创建成本是很低廉的，但是这也意味着，如果你修改了它的内容，
     * 也同时修改了其对应的源实例，所以要小心。
     *
     * 关于 ByteBuf 复制：
     * 如果需要一个现有缓冲区的真实副本，请使用 copy() 或者 copy(int, int) 方法。不同于派生缓冲区，由这个
     * 调用所返回的 ByteBuf 拥有独立的数据副本。
     *
     * 以 ByteBufOperations 中的 byteBufSlice() 方法为例，展示了如何使用 slice(int, int) 方法来操作
     * ByteBuf 的一个分段。
     *
     * 以 ByteBufOperations 中的 byteBufCopy() 方法为例，展示了 ByteBuf 的分段的副本和切片的区别。
     *
     * 除了修改原始 ByteBuf 的切片或者副本的效果以外，这两种场景是相同的。只要有可能，尽量使用 slice() 方法
     * 来避免复制内存的开销。
     *
     *
     *
     * 读/写操作
     *
     * 有两种类别的读/写操作：
     * （1）get() 和 set() 操作，从给定的索引开始，并且保持索引不变；
     * （2）read() 和 write() 操作，从给定的索引开始，并且会根据已经访问过的字节数对索引进行调整。
     *
     * 如下是最常用的 get() 方法：
     * （1）getBoolean(int)：返回给定索引处的 Boolean 值；
     * （2）getByte(int)：返回给定索引处的字节；
     * （3）getUnsignedByte(int)：将给定索引处的无符号字节值作为 short 返回；
     * （4）getMedium(int)：返回给定索引处的 24 位的中等 int 值；
     * （5）getUnsignedMedium(int)：返回给定索引处的无符号的 24 位的中等 int 值；
     * （6）getInt(int)：返回给定索引处的 int 值；
     * （7）getUnsignedInt(int)：将给定索引处的无符号 int 值作为 long 返回；
     * （8）getLong(int)：返回给定索引处的 long 值；
     * （9）getShort(int)：返回给定索引处的 short 值；
     * （10）getUnsignedShort(int)：将给定索引处的无符号 short 值作为 int 返回；
     * （11）getBytes(int, ...)：将该缓冲区中从给定索引开始的数据传送到指定的目的地。
     *
     * 大多数的这些操作都有一个对应的 set() 方法，如下：
     * （1）setBoolean(int, boolean)：设定给定索引处的 Boolean 值；
     * （2）setByte(int index, int value)：设定给定索引处的字节值；
     * （3）setMedium(int index, int value)：设定给定索引处的 24 位的中等 int 值；
     * （4）setInt(int index, int value)：设定给定索引处的 int 值；
     * （5）setLong(int index, long value)：设定给定索引处的 long 值；
     * （6）setShort(int index, int value)：设定给定索引处的 short 值。
     *
     * 以 ByteBufOperations 中的 byteBufSetGet() 方法为例，说明了 get() 和 set() 方法的用法，表明了它们
     * 不会改变读索引和写索引。
     *
     * 接下来研究一下 read() 操作，其作用于当前的 readerIndex 或 writerIndex。这些方法将用于从 ByteBuf 中
     * 读取数据，如同它是一个流。如下是最常用的 read() 方法：
     * （1）readBoolean()：返回当前 readerIndex 处的 Boolean，并将 readerIndex 增加 1；
     * （2）readByte()：返回当前 readerIndex 处的字节，并将 readerIndex 增加 1；
     * （3）readUnsignedByte()：将当前 readerIndex 处的无符号字节值作为 short 返回，并将 readerIndex 增加 1；
     * （4）readMedium()：返回当前 readerIndex 处的 24 位的中等 int 值，并将 readerIndex 增加 3；
     * （5）readUnsignedMedium()：返回当前 readerIndex 处的 24 位的无符号的中等 int 值，并将 readerIndex 增加 3；
     * （6）readInt()：返回当前 readerIndex 的 int 值，并将 readerIndex 增加 4；
     * （7）readUnsignedInt()：将当前 readerIndex 处的无符号的 int 值作为 long 值返回，并将 readerIndex 增加 4；
     * （8）readLong()：返回当前 readerIndex 处的 long 值，并将 readerIndex 增加 8；
     * （9）readShort()：返回当前 readerIndex 处的 short 值，并将 readerIndex 增加 2；
     * （10）readUnsignedShort()：将当前 readerIndex 处的无符号 short 值作为 int 值返回，并将 readerIndex 增加 2；
     * （11）readBytes(ByteBuf | byte[] destination, int dstIndex [, int length])：将当前 ByteBuf 中从当前
     * readerIndex 处开始的（如果设置了，length 长度的字节）数据传送到一个目标 ByteBuf 或者 byte[]，从目标的 dstIndex
     * 开始的位置。本地的 readerIndex 将被增加已经传输的字节数。
     *
     * 几乎每个 read() 方法都有对应的 write() 方法，用于将数据追加到 ByteBuf 中。注意，如下所列出的这些方法
     * 的参数是需要写入的值，而不是索引值。
     * （1）writeBoolean(boolean)：在当前 writerIndex 处写入一个 Boolean，并将 writerIndex 增加 1；
     * （2）writeByte(int)：在当前 writerIndex 处写入一个字节值，并将 writerIndex 增加 1；
     * （3）writeMedium(int)：在当前 writerIndex 处写入一个中等的 int 值，并将 writerIndex 增加 3；
     * （4）writeInt(int)：在当前 writerIndex 处写入一个 int 值，并将 writerIndex 增加 4；
     * （5）writeLong(long)：在当前 writerIndex 处写入一个 long 值，并将 writerIndex 增加 8；
     * （6）writeShort(int)：在当前 writerIndex 处写入一个 short 值，并将 writerIndex 增加 2；
     * （7）writeBytes(source ByteBuf | byte[] [, int srcIndex, int length])：从当前 writerIndex 开始，
     * 传输来自于指定源（ByteBuf 或者 byte[]）的数据。如果提供了 srcIndex 和 length，则从 srcIndex 开始读取，
     * 并且处理长度为 length 的字节。当前 writerIndex 将会被增加所写入的字节数。
     *
     * 以 ByteBufOperations 中的 byteBufWriteRead() 方法为例，说明了 read() 和 write() 方法的用法。
     *
     *
     *
     * 更多的操作
     *
     * 如下列举了由 ByteBuf 提供的其他有用操作：
     * （1）isReadable()：如果至少有一个字节可供读取，则返回 true；
     * （2）isWritable()：如果至少有一个字节可被写入，则返回 true；
     * （3）readableBytes()：返回可被读取的字节数；
     * （4）writableBytes()：返回可被写入的字节数；
     * （5）capacity()：返回 ByteBuf 可容纳的字节数。在此之后，它会尝试再次扩展直 到达到 maxCapacity()；
     * （6）maxCapacity()：返回 ByteBuf 可以容纳的最大字节数；
     * （7）hasArray()：如果 ByteBuf 由一个字节数组支撑，则返回 true；
     * （8）array()：如果 ByteBuf 由一个字节数组支撑则返回该数组。否则，它将抛出一个 UnsupportedOperationException 异常。
     */
    public static void main(String[] args) {

    }

}
