package com.siwuxie095.network.chapter6th.example7th;

/**
 * @author Jiajing Li
 * @date 2020-11-16 07:37:10
 */
public class Main {

    /**
     * 资源管理
     *
     * 每当通过调用 ChannelInboundHandler.channelRead() 或者 ChannelOutboundHandler.write() 方法来处理数据时，
     * 你都需要确保没有任何的资源泄漏。Netty 使用引用计数来处理池化的 ByteBuf。所以在完全使用完某个 ByteBuf 后，调整
     * 其引用计数是很重要的。
     *
     * 为了帮助你诊断潜在的（资源泄漏）问题，Netty提供了 ResourceLeakDetector 类（其利用了 JDK 提供的 PhantomReference<T>
     * 类来实现这一点），它将对你应用程序的缓冲区分配做大约 1% 的采样来检测内存泄露。相关的开销是非常小的。
     *
     * Netty 目前定义了 4 种泄漏检测级别，如下：
     * （1）DISABLED：禁用泄漏检测。只有在详尽的测试之后才应设置为这个值。
     * （2）SIMPLE：使用 1% 的默认采样率检测并报告任何发现的泄露。这是默认级别，适合绝大部分的情况。
     * （3）ADVANCED：使用默认的采样率，报告所发现的任何的泄露以及对应的消息被访问的位置。
     * （4）PARANOID：类似于 ADVANCED，但是其将会对每次（对消息的）访问都进行采样。这对性能将会有很大的影响，应该只在
     * 调试阶段使用。
     *
     * 泄露检测级别可以通过将下面的 Java 系统属性设置为其中的一个值来定义，如下：
     *
     * java -Dio.netty.leakDetectionLevel=ADVANCED
     *
     * 如果带着该 JVM 选项重新启动你的应用程序，你将看到自己的应用程序最近被泄漏的缓冲区被访问的位置。下面是一个典型的
     * 由单元测试产生的泄漏报告：
     *
     * Running io.netty.handler.codec.xml.XmlFrameDecoderTest
     * 15:03:36.886 [main] ERROR io.netty.util.ResourceLeakDetector - LEAK:
     * ByteBuf.release() was not called before it's garbage-collected.
     * Recent access records: 1
     * #1: io.netty.buffer.AdvancedLeakAwareByteBuf.toString(
     *      AdvancedLeakAwareByteBuf.java:697)
     * io.netty.handler.codec.xml.XmlFrameDecoderTest.testDecodeWithXml(
     *      XmlFrameDecoderTest.java:157)
     * io.netty.handler.codec.xml.XmlFrameDecoderTest.testDecodeWithTwoMessages(
     *      XmlFrameDecoderTest.java:133)
     * ...
     *
     * 实现 ChannelInboundHandler.channelRead() 和 ChannelOutboundHandler.write() 方法时，应该如何使用这个
     * 诊断工具来防止泄露呢？以 DiscardInboundHandler 为例，来看看 channelRead() 操作直接消费入站消息的情况。此
     * 时，直接释放了消息，也就是说，它不会通过调用 ChannelHandlerContext.fireChannelRead() 方法将入站消息转发
     * 给下一个 ChannelInboundHandler。
     *
     * 关于消费入站消息的简单方式：
     * 由于消费入站数据是一项常规任务，所以 Netty 提供了一个特殊的被称为 SimpleChannelInboundHandler 的
     * ChannelInboundHandler 实现。这个实现会在消息被 channelRead0() 方法消费之后自动释放消息。
     *
     * 在出站方向这边，如果你处理了 write() 操作并丢弃了一个消息，那么你也应该负责释放它。以 DiscardOutboundHandler
     * 为例，展示了一个丢弃所有的写入数据的实现。
     *
     * 重要的是，不仅要释放资源，还要通知 ChannelPromise。否则可能会出现 ChannelFutureListener 收不到某个消息已
     * 经被处理了的通知的情况。
     *
     * 总之，如果一个消息被消费或者丢弃了，并且没有传递给 ChannelPipeline 中的下一个 ChannelOutboundHandler，那
     * 么用户就有责任调用 ReferenceCountUtil.release()。如果消息到达了实际的传输层，那么当它被写入时或者 Channel
     * 关闭时，都将被自动释放。
     */
    public static void main(String[] args) {

    }

}
