package com.siwuxie095.network.chapter11th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-29 15:00:44
 */
public class Main {

    /**
     * 解码基于分隔符的协议和基于长度的协议
     *
     * 在使用 Netty 的过程中，你将会遇到需要解码器的基于分隔符和帧长度的协议。下面将解释 Netty 所提供的用于
     * 处理这些场景的实现。
     *
     *
     *
     * 基于分隔符的协议
     *
     * 基于分隔符的（delimited）消息协议使用定义的字符来标记的消息或者消息段（通常被称为帧）的开头或者结尾。
     * 由 RFC 文档正式定义的许多协议（如 SMTP、POP3、IMAP 以及 Telnet）都是这样的。此外，私有组织通常也拥
     * 有他们自己的专有格式。无论你使用什么样的协议，下面列出的解码器都能帮助你定义可以提取由任意标记（token）
     * 序列分隔的帧的自定义解码器。
     * （1）DelimiterBasedFrameDecoder：使用任何由用户提供的分隔符来提取帧的通用解码器。
     * （2）LineBasedFrameDecoder：提取由行尾符（\n 或者\r\n）分隔的帧的解码器。这个解码器比
     * DelimiterBasedFrameDecoder 更快。
     *
     * PS：有关这些协议的 RFC 可以在 IETF 的网站上找到：
     * （1）SMTP 在 https://www.ietf.org/rfc/rfc2821.txt。
     * （2）POP3 在 https://www.ietf.org/rfc/rfc1939.txt。
     * （3）IMAP 在 https://tools.ietf.org/html/rfc3501。
     * （4）Telnet 在 https://tools.ietf.org/search/rfc854。
     *
     * 以 LineBasedHandlerInitializer 为例，展示了如何使用 LineBasedFrameDecoder 来处理由行尾符分隔的
     * 帧。
     *
     * 如果你正在使用除了行尾符之外的分隔符分隔的帧，那么你可以以类似的方式使用 DelimiterBasedFrameDecoder，
     * 只需要将特定的分隔符序列指定到其构造函数即可。
     *
     * 这些解码器是实现你自己的基于分隔符的协议的工具。以 CmdHandlerInitializer 为例，将使用下面的协议规范：
     * （1）传入数据流是一系列的帧，每个帧都由换行符（\n）分隔；
     * （2）每个帧都由一系列的元素组成，每个元素都由单个空格字符分隔；
     * （3）一个帧的内容代表一个命令，定义为一个命令名称后跟着数目可变的参数。
     *
     * 用于这个协议的自定义解码器将定义以下类：
     * （1）Cmd：将帧（命令）的内容存储在 ByteBuf 中，一个 ByteBuf 用于名称，另一个用于参数；
     * （2）CmdDecoder：从被重写了的 decode() 方法中获取一行字符串，并从它的内容构建一个 Cmd 的实例；
     * （3）CmdHandler：从 CmdDecoder 获取解码的 Cmd 对象，并对它进行一些处理；
     * （4）CmdHandlerInitializer：为了简便起见，将会把前面的这些类定义为专门的 ChannelInitializer 的
     * 嵌套类，其将会把这些 ChannelInboundHandler 安装到 ChannelPipeline 中。
     *
     * 这个解码器的关键是扩展了 LineBasedFrameDecoder。
     *
     *
     *
     * 基于长度的协议
     *
     * 基于长度的协议通过将它的长度编码到帧的头部来定义帧，而不是使用特殊的分隔符来标记它的结束（注意：对于固定
     * 帧大小的协议来说，不需要将帧长度编码到头部）。如下列出了 Netty 提供的用于处理这种类型的协议的两种解码器。
     * （1）FixedLengthFrameDecoder：提取在调用构造函数时指定的定长帧。
     * （2）LengthFieldBasedFrameDecoder：根据编码进帧头部中的长度值提取帧，该字段的偏移量以及长度在构造函
     * 数中指定。
     *
     * 你将经常会遇到被编码到消息头部的帧大小不是固定值的协议。为了处理这种变长帧，你可以使用
     * LengthFieldBasedFrameDecoder，它将从头部字段确定帧长，然后从数据流中提取指定的字节数。
     *
     * LengthFieldBasedFrameDecoder 提供了几个构造函数来支持各种各样的头部配置情况。以 LengthBasedInitializer
     * 为例，展示了如何使用其 3 个构造参数分别为 maxFrameLength、lengthFieldOffset 和 lengthFieldLength
     * 的构造函数。在这个场景中，帧的长度被编码到了帧起始的前 8 个字节中。
     *
     *
     *
     * 你现在已经看到了 Netty 提供的，用于支持那些通过指定协议帧的分隔符或者长度（固定的或者可变的）以定义字节
     * 流的结构的协议的编解码器。你将会发现这些编解码器的许多用途，因为许多的常见协议都落到了这些分类之一中。
     */
    public static void main(String[] args) {

    }

}
