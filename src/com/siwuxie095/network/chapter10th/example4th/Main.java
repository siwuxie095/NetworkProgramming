package com.siwuxie095.network.chapter10th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-25 07:38:36
 */
public class Main {

    /**
     * 编码器
     *
     * 编码器实现了 ChannelOutboundHandler，并将出站数据从一种格式转换为另一种格式。Netty 提供了一组类，用于
     * 帮助编写具有以下功能的编码器：
     * （1）将消息编码为字节。
     * （2）将消息编码为消息（另外一种格式的消息）。
     *
     *
     *
     * 抽象类 MessageToByteEncoder
     *
     * ByteToMessageDecoder 可以将字节转换为消息。而这里将使用 MessageToByteEncoder 来做逆向的事情。如下是
     * MessageToByteEncoder 的 API：
     *
     *     protected abstract void encode(ChannelHandlerContext ctx, I msg, ByteBuf out)
     *
     * encode() 方法是你需要实现的唯一抽象方法。它被调用时将会传入要被该类编码为 ByteBuf 的（类型为 I 的）出站
     * 消息。该 ByteBuf 随后将会被转发给 ChannelPipeline 中的下一个 ChannelOutboundHandler。
     *
     * 你可能已经注意到了，这个类（编码器）只有一个方法，而解码器有两个。原因是解码器通常需要在 Channel 关闭之后
     * 产生最后一个消息（因此也就有了 decodeLast() 方法）。这显然不适用于编码器的场景 —— 在连接被关闭之后仍然产
     * 生一个消息是毫无意义的。
     *
     * 以 ShortToByteEncoder 为例，其接受一个 Short 类型的实例作为消息，将它编码为 Short 的原子类型值，并将
     * 它写入 ByteBuf 中，其将随后被转发给 ChannelPipeline 中的下一个 ChannelOutboundHandler。每个传出的
     * Short 值都将会占用 ByteBuf 中的 2 字节。
     *
     * Netty 提供了一些专门化的 MessageToByteEncoder，你可以基于它们实现自己的编码器。WebSocket08FrameEncoder
     * 类提供了一个很好的实例。你可以在 io.netty.handler.codec.http.websocketx 包中找到它。
     *
     * （很奇怪，WebSocket08FrameEncoder 继承自 MessageToMessageEncoder，而不是 MessageToByteEncoder，
     * 但却在这里说？？？不过无所谓，知道这件事即可）
     *
     *
     *
     * 抽象类 MessageToMessageEncoder
     *
     * MessageToMessageEncoder 类可以将出站数据从一种消息编码为另一种。它的 encode() 提供了这种能力：
     *
     *     protected abstract void encode(ChannelHandlerContext ctx, I msg, List<Object> out)
     *
     * 这是你需要实现的唯一方法。每个通过 write() 方法写入的消息都将会被传递给 encode() 方法，以编码为一个或者
     * 多个出站消息。随后，这些出站消息将会被转发给 ChannelPipeline 中的下一个 ChannelOutboundHandler。
     *
     * 以 IntegerToStringEncoder 为例，它扩展了 MessageToMessageEncoder。编码器将每个出站 Integer 的
     * String 表示添加到了该 List 中。
     *
     * 关于有趣的 MessageToMessageEncoder 的专业用法，请查看 io.netty.handler.codec.protobuf.
     * ProtobufEncoder 类，它处理了由 Google 的 ProtocolBuffers 规范所定义的数据格式。
     */
    public static void main(String[] args) {

    }

}
