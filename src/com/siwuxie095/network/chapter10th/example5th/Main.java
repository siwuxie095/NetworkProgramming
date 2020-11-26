package com.siwuxie095.network.chapter10th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-25 08:12:48
 */
public class Main {

    /**
     * 抽象的编解码器类
     *
     * 虽然一直将解码器和编码器作为单独的实体讨论，但是你有时将会发现在同一个类中管理入站和出站数据和消息的转换是很有用的。
     * Netty 的抽象编解码器类正好用于这个目的，因为它们每个都将捆绑一个解码器/编码器对，以处理这两种类型的操作。正如同你
     * 可能已经猜想到的，这些类同时实现了 ChannelInboundHandler 和 ChannelOutboundHandler 接口。
     *
     * 为什么这里并没有优先于单独的解码器和编码器使用这些复合类呢？因为通过尽可能地将这两种功能分开，最大化了代码的可重用
     * 性和可扩展性，这是 Netty 设计的一个基本原则。
     *
     * 在查看这些抽象的编解码器类时，将会把它们与相应的单独的解码器和编码器进行比较和参照。
     *
     *
     *
     * 抽象类 ByteToMessageCodec
     *
     *来研究这样的一个场景：需要将字节解码为某种形式的消息，可能是 POJO，随后再次对它进行编码。ByteToMessageCodec 将
     * 处理好这一切，因为它结合了 ByteToMessageDecoder 以及它的逆向 —— MessageToByteEncoder。
     *
     * 如下列出了其中重要的方法。
     * （1）decode(ChannelHandlerContext ctx, ByteBuf in, List<Object>)：
     * 只要有字节可以被消费，这个方法就将会被调用。它将入站 ByteBuf 转换为指定的消息格式，并将其转发给 ChannelPipeline
     * 中的下一个 ChannelInboundHandler。
     *
     * （2）decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)：
     * 这个方法的默认实现委托给了 decode() 方法。它只会在 Channel 的状态变为非活动（非活跃）时被调用一次。它可以被重写
     * 以实现特殊的处理。
     *
     * （3）encode(ChannelHandlerContext ctx, I msg, ByteBuf out)：
     * 对于每个将被编码并写入出站 ByteBuf 的（类型为 I 的）消息来说，这个方法都将会被调用。
     *
     * 任何的请求/响应协议都可以作为使用 ByteToMessageCodec 的理想选择。例如，在某个 SMTP 的实现中，编解码器将读取传
     * 入字节，并将它们解码为一个自定义的消息类型，如 SmtpRequest。而在接收端，当一个响应被创建时，将会产生一个
     * SmtpResponse，其将被编码回字节以便进行传输。
     *
     * PS：SmtpRequest 位于基于 Netty 的 SMTP/LMTP 客户端项目中 https://github.com/normanmaurer/niosmtp。
     *
     *
     *
     * 抽象类 MessageToMessageCodec
     *
     * 通过使用 MessageToMessageCodec，可以在一个单个的类中实现将一种消息格式转换为另外一种消息格式的往返过程。
     *
     * MessageToMessageCodec 是一个参数化的类，定义如下：
     *
     * public abstract class MessageToMessageCodec<INBOUND_IN, OUTBOUND_IN> extends ChannelDuplexHandler
     *
     * 如下列出了其中重要的方法。
     * （1）protected abstract decode(ChannelHandlerContext ctx, INBOUND_IN msg, List<Object> out)：
     * 这个方法被调用时会被传入 INBOUND_IN 类型的消息。它将把它们解码为 OUTBOUND_IN 类型的消息，这些消息将被转发给
     * ChannelPipeline 中的下一个 ChannelInboundHandler。
     *
     * （2）protected abstract encode(ChannelHandlerContext ctx, OUTBOUND_IN msg, List<Object> out)：
     * 对于每个 OUTBOUND_IN 类型的消息，这个方法都将会被调用。这些消息将会被编码为 INBOUND_IN 类型的消息，然后被转发
     * 给 ChannelPipeline 中的下一个 ChannelOutboundHandler。
     *
     * decode() 方法是将 INBOUND_IN 类型的消息转换为 OUTBOUND_IN 类型的消息，而 encode() 方法则进行它的逆向操作。
     * 将 INBOUND_IN 类型的消息看作是通过网络发送的类型，而将 OUTBOUND_IN 类型的消息看作是应用程序所处理的类型，将可
     * 能有所裨益（即 有助于理解这两个类型签名的实际意义）。
     *
     * 虽然这个编解码器可能看起来有点高深，但是它所处理的用例却是相当常见的：在两种不同的消息 API 之间来回转换数据。当不
     * 得不和使用遗留或者专有消息格式的 API 进行互操作时，经常会遇到这种模式。
     *
     * 以 WebSocketConvertHandler 为例，这个示例引用了一个新出的 WebSocket 协议，这个协议能实现 Web 浏览器和服务器
     * 之间的全双向通信。展示了这样的对话（指 Web 浏览器和服务器之间的双向通信）可能的实现方式。
     *
     * WebSocketConvertHandler 在参数化 MessageToMessageCodec 时将使用 INBOUND_IN 类型的 WebSocketFrame，以及
     * OUTBOUND_IN 类型的 MyWebSocketFrame，后者是 WebSocketConvertHandler 本身的一个静态嵌套类。
     *
     *
     *
     * CombinedChannelDuplexHandler 类
     *
     * 结合一个解码器和编码器可能会对可重用性造成影响。但是，有一种方法既能够避免这种惩罚，又不会牺牲将一个解码器和一个编
     * 码器作为一个单独的单元部署所带来的便利性。CombinedChannelDuplexHandler 提供了这个解决方案，其声明为：
     *
     * public class CombinedChannelDuplexHandler<I extends ChannelInboundHandler, O extends ChannelOutboundHandler>
     *         extends ChannelDuplexHandler
     *
     * 这个类充当了 ChannelInboundHandler 和 ChannelOutboundHandler（该类的类型 参数 I 和 O）的容器。通过提供分别
     * 继承了解码器类和编码器类的类型，可以实现一个编解码器，而又不必直接扩展抽象的编解码器类。
     *
     * 以 ByteToCharDecoder 为例。注意，该实现扩展了 ByteToMessageDecoder，因为它要从 ByteBuf 中读取字符。
     *
     * 这里的 decode() 方法一次将从 ByteBuf 中提取 2 字节，并将它们作为 char 写入到 List 中，其将会被自动装箱为
     * Character 对象。
     *
     * 以 CharToByteEncoder 为例。它能将 Character 转换回字节。这个类扩展了 MessageToByteEncoder，因为它需要将
     * char 消息编码到 ByteBuf 中。这是通过直接写入 ByteBuf 做到的。
     *
     * 既然有了解码器和编码器，就可以结合它们来构建一个编解码器。以 CombinedByteCharCodec 为例，展示了这是如何做到的。
     *
     * 正如你所能看到的，在某些情况下，通过这种方式结合实现相对于使用编解码器类的方式来说可能更加的简单也更加的灵活。当然，
     * 这可能也归结于个人的偏好问题。
     */
    public static void main(String[] args) {

    }

}
