package com.siwuxie095.network.chapter10th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-23 07:56:19
 */
public class Main {

    /**
     * 解码器
     *
     * 这里将研究 Netty 所提供的解码器类，并提供关于何时以及如何使用它们的具体示例。这些类覆盖了两个不同的用例：
     * （1）将字节解码为消息：ByteToMessageDecoder 和 ReplayingDecoder。
     * （2）将一种消息类型解码为另一种：MessageToMessageDecoder。
     *
     * 因为解码器是负责将入站数据从一种格式转换到另一种格式的，所以知道 Netty 的解码器实现了
     * ChannelInboundHandler 也不会让你感到意外。
     *
     * 什么时候会用到解码器呢？很简单：每当需要为 ChannelPipeline 中的下一个 ChannelInboundHandler 转换
     * 入站数据时会用到。此外，得益于 ChannelPipeline 的设计，可以将多个解码器链接在一起，以实现任意复杂的
     * 转换逻辑，这也是 Netty 是如何支持代码的模块化以及复用的一个很好的例子。
     *
     *
     *
     * 抽象类 ByteToMessageDecoder
     *
     * 将字节解码为消息（或者另一个字节序列）是一项如此常见的任务，以至于 Netty 为它提供了一个抽象的基类：
     * ByteToMessageDecoder。由于你不可能知道远程节点是否会一次性地发送一个完整 的消息，所以这个类会对入站
     * 数据进行缓冲，直到它准备好处理。如下是它最重要的两个方法。
     *
     * （1）decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)：
     * 这是你必须实现的唯一抽象方法。decode() 方法被调用时将会传入一个包含了传入数据的 ByteBuf，以及一个用来
     * 添加解码消息的 List。对这个方法的调用将会重复进行，直到确定没有新的元素被添加到该 List，或者该 ByteBuf
     * 中没有更多可读取的字节时为止。然后，如果该 List 不为空，那么它的内容将会被传递给 ChannelPipeline 中
     * 的下一个 ChannelInboundHandler。
     *
     * （2）decodeLast( ChannelHandlerContext ctx, ByteBuf in, List<Object> out)：
     * Netty 提供的这个默认实现只是简单地调用了 decode() 方法。 当 Channel 的状态变为非活动（活跃）时，这个
     * 方法将会被调用一次。可以重写该方法以提供特殊的处理（比如用来产生一个 LastHttpContent 消息）。
     *
     * 以 ToIntegerDecoder 为例，假设你接收了一个包含简单 int 的字节流，每个 int 都需要被单独处理。在这种情
     * 况下，你需要从入站 ByteBuf 中读取每个 int，并将它传递给 ChannelPipeline 中的下一个
     * ChannelInboundHandler。为了解码这个字节流，就要扩展 ByteToMessageDecoder 类（需要注意的是，原子类
     * 型的 int 在被添加到 List 中时，会被自动装箱为 Integer）。
     *
     * 虽然 ByteToMessageDecoder 使得可以很简单地实现这种模式，但是你可能会发现，在调用 readInt() 方法前不
     * 得不验证所输入的 ByteBuf 是否具有足够的数据有点繁琐。而如果使用 ReplayingDecoder，一个特殊的解码器，
     * 则能以少量的开销消除了这个步骤。
     *
     * 关于编解码器中的引用计数：
     * 引用计数需要特别的注意。对于编码器和解码器来说，其过程也是相当的简单：一旦消息被编码或者解码，它就会被
     * ReferenceCountUtil.release(message) 调用自动释放。如果你需要保留引用以便稍后使用，那么你可以调用
     * ReferenceCountUtil.retain(message) 方法。这将会增加该引用计数，从而防止该消息被释放。
     *
     *
     *
     * 抽象类 ReplayingDecoder
     *
     * ReplayingDecoder 扩展了 ByteToMessageDecoder 类，使得不必调用 readableBytes() 方法。它通过使用
     * 一个自定义的 ByteBuf 实现，即 ReplayingDecoderByteBuf，包装传入的 ByteBuf 实现了这一点，其将在内
     * 部执行该调用（指调用 readableBytes() 方法）。
     *
     * 这个类的完整声明是：
     *
     * public abstract class ReplayingDecoder<S> extends ByteToMessageDecoder
     *
     * 类型参数 S 指定了用于状态管理的类型，其中 Void 代表不需要状态管理。
     *
     * 以 ToIntegerDecoder2 为例，展示了基于 ReplayingDecoder 重新实现的 ToIntegerDecoder。
     *
     * 和之前一样，从 ByteBuf 中提取的 int 将会被添加到 List 中。如果没有足够的字节可用，这个 readInt() 方
     * 法的实现将会抛出一个Error（这里实际上抛出的是一个 Signal，详见 io.netty.util.Signal 类），其将在基
     * 类中被捕获并处理。当有更多的数据可供读取时，该 decode() 方法将会被再次调用。
     *
     * 请注意 ReplayingDecoderByteBuf 的这些方面：
     * （1）并不是所有的 ByteBuf 操作都被支持，如果调用了一个不被支持的方法，将会抛出一个
     * UnsupportedOperationException。
     * （2）ReplayingDecoder 稍慢于 ByteToMessageDecoder。
     *
     * 通过对比，显然，ToIntegerDecoder2 要比 ToIntegerDecoder 更简单。
     *
     * 示例本身是很基本的，所以请记住，在真实的、更加复杂的情况下，使用一种或者另一种作为基类所带来的差异可能是
     * 很显著的。这里有一个简单的准则：如果使用 ByteToMessageDecoder 不会引入太多的复杂性，那么请使用它；否
     * 则，请使用 ReplayingDecoder。
     *
     * 更多的解码器：下面的这些类能处理更加复杂的用例。
     * （1）io.netty.handler.codec.LineBasedFrameDecoder — 这个类在 Netty 内部也有使用，它使用了行尾
     * 控制字符（\n 或者\r\n）来解析消息数据。
     * （2）io.netty.handler.codec.http.HttpObjectDecoder — 一个 HTTP 数据的解码器。
     *
     * 在 io.netty.handler.codec 子包下面，你将会发现更多用于特定用例的编码器和解码器实现。
     *
     *
     *
     * 抽象类 MessageToMessageDecoder
     *
     * 这里将解释如何使用抽象基类 MessageToMessageDecoder 在两个消息格式之间进行转换（例如， 从一种 POJO
     * 类型转换为另一种）：
     *
     * public abstract class MessageToMessageDecoder<I> extends ChannelInboundHandlerAdapter
     *
     * 类型参数 I 指定了 decode() 方法的输入参数 msg 的类型，它是你必须实现的唯一方法。如下：
     *
     *     protected abstract void decode(ChannelHandlerContext ctx, I msg, List<Object> out)
     *
     * 对于每个需要被解码为另一种格式的入站消息来说，该方法都将会被调用。解码消息随后会被传递给 ChannelPipeline
     * 中的下一个 ChannelInboundHandler。
     *
     * 以 IntegerToStringDecoder 为例，它扩展了 MessageToMessageDecoder<Integer>。它的 decode() 方法
     * 会把 Integer 参数转换为它的 String 表示，并将拥有下列签名：
     *
     *     public void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception
     *
     * 解码的 String 将被添加到传出的 List 中，并转发给下一个 ChannelInboundHandler。
     *
     * 关于 HttpObjectAggregator：
     * 有关更加复杂的例子，请研究 io.netty.handler.codec.http.HttpObjectAggregator 类， 它扩展了
     * MessageToMessageDecoder<HttpObject>。
     *
     *
     *
     * TooLongFrameException 类
     *
     * 由于 Netty 是一个异步框架，所以需要在字节可以解码之前在内存中缓冲它们。因此，不能让解码器缓冲大量的数据
     * 以至于耗尽可用的内存。为了解除这个常见的顾虑，Netty 提供了 TooLongFrameException 类，其将由解码器在
     * 帧超出指定的大小限制时抛出。
     *
     * 为了避免这种情况，可以设置一个最大字节数的阈值，如果超出该阈值，则会导致抛出一个 TooLongFrameException
     * （随后会被 ChannelHandler.exceptionCaught() 方法捕获）。然后，如何处理该异常则完全取决于该解码器的
     * 用户。某些协议（如 HTTP）可能允许你返回一个特殊的响应。而在其他的情况下，唯一的选择可能就是关闭对应的连接。
     *
     * 以 SafeByteToMessageDecoder 为例，展示了 ByteToMessageDecoder 是如何使用 TooLongFrameException
     * 来通知 ChannelPipeline 中的其他 ChannelHandler 发生了帧大小溢出的。需要注意的是，如果你正在使用一个
     * 可变帧大小的协议，那么这种保护措施将是尤为重要的。
     */
    public static void main(String[] args) {

    }

}
