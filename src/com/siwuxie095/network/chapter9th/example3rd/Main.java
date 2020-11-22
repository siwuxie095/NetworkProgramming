package com.siwuxie095.network.chapter9th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-22 21:25:24
 */
public class Main {

    /**
     * 使用 EmbeddedChannel 测试 ChannelHandler
     *
     * 这里将讲解如何使用 EmbeddedChannel 来测试 ChannelHandler。
     *
     * 关于 JUnit 断言：
     * org.junit.Assert 类提供了很多用于测试的静态方法。失败的断言将导致一个异常被抛出，并将终止当前正在执行中的测试。
     * 导入这些断言的最高效的方式是通过一个 import static 语句来实现：
     *
     * import static org.junit.Assert.*;
     *
     * 一旦这样做了，就可以直接调用 Assert 方法了，如下：
     *
     * assertEquals(buf.readSlice(3), read);
     *
     *
     *
     * 测试入站消息
     *
     * 以 FixedLengthFrameDecoder 为例，它是一个简单的 ByteToMessageDecoder 实现。给定足够的数据，这个实现将产生
     * 固定大小的帧。如果没有足够的数据可供读取，它将等待下一个数据块的到来，并将再次检查是否能够产生一个新的帧。
     *
     * 因为这个特定的解码器将产生固定大小的帧，所以它可能会需要多个事件来提供足够的字节数以产生一个帧。
     *
     * 最终，每个帧都会被传递给 ChannelPipeline 中的下一个 ChannelHandler。
     *
     * 现在，创建一个单元测试，以确保这段代码将按照预期执行。即使是在简单的代码中，单元测试也能帮助防止在将来代码重构时
     * 可能会导致的问题，并且能在问题发生时帮助诊断它们。
     *
     * 以 FixedLengthFrameDecoderTest 为例，展示了一个使用 EmbeddedChannel 对前面代码的测试。
     *
     * 其中 testFramesDecoded() 方法验证了：一个包含 9 个可读字节的 ByteBuf 被解码为 3 个 ByteBuf，每个都包含了
     * 3 字节。需要注意的是，仅通过一次对 writeInbound() 方法的调用，ByteBuf 是如何被填充了 9 个可读字节的。在此之
     * 后，通过执行 finish() 方法，将 EmbeddedChannel 标记为了已完成状态。最后，通过调用 readInbound()方法，从
     * EmbeddedChannel 中正好读取了 3 个帧和一个 null。
     *
     * testFramesDecoded2() 方法也是类似的，只有一处不同：入站 ByteBuf 是通过两个步骤写入的。当
     * writeInbound(input.readBytes(2)) 被调用时，返回了 false。为什么呢？如果对 readInbound() 的后续调用将会
     * 返回数据，那么 writeInbound() 方法将会返回 true。但是只有当有 3 个或者更多的字节可供读取时，
     * FixedLengthFrameDecoder 才会产生输出。该测试剩下的部分和 testFramesDecoded() 是相同的。
     *
     *
     *
     * 测试出站消息
     *
     * 测试出站消息的处理过程和上面所看到的类似。以 AbsIntegerEncoder 为例，它是 Netty 的 MessageToMessageEncoder
     * 的一个特殊化的实现，用于将负值整数转换为绝对值。（PS：编码器是一种将一种消息格式转换为另一种的组件）。
     *
     * 该示例将会按照下列方式工作：
     * （1）持有 AbsIntegerEncoder 的 EmbeddedChannel 将会以 4 字节的负整数的形式写出站数据；
     * （2）编码器将从传入的 ByteBuf 中读取每个负整数，并将会调用 Math.abs() 方法来获取其绝对值；
     * （3）编码器将会把每个负整数的绝对值写到 ChannelPipeline 中。
     *
     * 以 AbsIntegerEncoderTest 为例，展示了如何使用 EmbeddedChannel 来测试一个编码器形式的 ChannelOutboundHandler。
     *
     * 下面是代码中执行的步骤：
     * （1）将 4 字节的负整数写到一个新的 ByteBuf 中；
     * （2）创建一个 EmbeddedChannel，并为它分配一个 AbsIntegerEncoder；
     * （3）调用 EmbeddedChannel 上的 writeOutbound() 方法来写入该 ByteBuf；
     * （4）标记该 Channel 为已完成状态；
     * （5）从 EmbeddedChannel 的出站端读取所有的整数，并验证是否只产生了绝对值。
     */
    public static void main(String[] args) {

    }

}
