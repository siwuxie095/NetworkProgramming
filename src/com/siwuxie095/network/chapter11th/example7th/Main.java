package com.siwuxie095.network.chapter11th.example7th;

/**
 * @author Jiajing Li
 * @date 2020-11-29 16:51:10
 */
public class Main {

    /**
     * 序列化数据
     *
     * JDK 提供了 ObjectOutputStream 和 ObjectInputStream，用于通过网络对 POJO 的基本数据类型和图进行序列化和反序列化。
     * 该 API 并不复杂，而且可以被应用于任何实现了 java.io.Serializable 接口的对象。但是它的性能也不是非常高效的。这里将
     * 看到 Netty 必须为此提供什么。
     *
     *
     *
     * JDK 序列化
     *
     * 如果你的应用程序必须要和使用了 ObjectOutputStream 和 ObjectInputStream 的远程节点交互，并且兼容性也是你最关心的，
     * 那么 JDK 序列化将是正确的选择。
     *
     * PS：参见 Oracle 的 Java SE 文档中的 "Java Object Serialization" 部分：
     * https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/
     *
     * 如下列出了 Netty 提供的用于和 JDK 进行互操作的序列化类。
     * （1）CompatibleObjectDecoder：和使用 JDK 序列化的非基于 Netty 的远程节点进行互操作的解码器。
     * （2）CompatibleObjectEncoder：和使用 JDK 序列化的非基于 Netty 的远程节点进行互操作的编码器。
     * （3）ObjectDecoder：构建于 JDK 序列化之上的使用自定义的序列化来解码的解码器。当没有其他的外部依赖时，它提供了速度上
     * 的改进。否则其他的序列化实现更加可取。
     * （4）ObjectEncoder：构建于 JDK 序列化之上的使用自定义的序列化来编码的编码器。当没有其他的外部依赖时，它提供了速度上
     * 的改进。否则其他的序列化实现更加可取。
     *
     * PS：CompatibleObjectDecoder 类已经在 Netty 3.1 中废弃，并不存在于 Netty 4.x 中：
     * https://issues.jboss.org/browse/NETTY-136 或 https://issues.redhat.com/browse/NETTY-136?_sscc=t
     *
     *
     *
     * 使用 JBoss Marshalling 进行序列化
     *
     * 如果你可以自由地使用外部依赖，那么 JBoss Marshalling 将是个理想的选择：它比 JDK 序列化最多快 3 倍，而且也更加紧凑。
     * 在 JBoss Marshalling 官方网站主页上的概述中对它是这么定义的：
     *
     * JBoss Marshalling 是一种可选的序列化 API，它修复了在 JDK 序列化 API 中所发现的许多问题，同时保留了与
     * java.io.Serializable 及其相关类的兼容性，并添加了几个新的可调优参数以及额外的特性，所有的这些都是可以通过工厂配置
     * （如外部序列化器、类/实例查找表、类解析以及对象替换等）实现可插拔的。
     *
     * PS：更多关于 JBoss Marshalling 可参见：
     * http://www.jboss.org/jbossmarshalling 或 https://jbossmarshalling.jboss.org/
     *
     * Netty 通过如下所示的两组解码器/编码器对为 Boss Marshalling 提供了支持。第一组兼容只使用 JDK 序列化的远程节点。第
     * 二组提供了最大的性能，适用于和使用 JBoss Marshalling 的远程节点一起使用。
     * （1）与只使用 JDK 序列化的远程节点兼容：
     * 1）CompatibleMarshallingDecoder
     * 2）CompatibleMarshallingEncoder
     *
     * （2）适用于使用 JBoss Marshalling 的节点。这些类必须一起使用：
     * 1）MarshallingDecoder
     * 2）MarshallingEncoder
     *
     * 以 MarshallingInitializer 为例，展示了如何使用 MarshallingDecoder 和 MarshallingEncoder。几乎只是适当地配置
     * ChannelPipeline 罢了。
     *
     *
     *
     * 通过 Protocol Buffers 序列化
     *
     * Netty 序列化的最后一个解决方案是利用 Protocol Buffers 的编解码器，它是一种由 Google 公司开发的、现在已经开源的数
     * 据交换格式。可以在 https://github.com/google/protobuf 找到源代码。
     *
     * PS：有关 Protocol Buffers 的描述请参考 https://developers.google.com/protocol-buffers/?hl=zh
     *
     * Protocol Buffers 以一种紧凑而高效的方式对结构化的数据进行编码以及解码。它具有许多的编程语言绑定，使得它很适合跨语言
     * 的项目。如下展示了 Netty 为支持 protobuf 所提供的 ChannelHandler 实现。
     * （1）ProtobufDecoder：使用 protobuf 对消息进行解码。
     * （2）ProtobufEncoder：使用 protobuf 对消息进行编码。
     * （3）ProtobufVarint32FrameDecoder：根据消息中的 Google Protocol Buffers 的 "Base 128 Varints" 整型长度字
     * 段值动态地分割所接收到的 ByteBuf。
     * （4）ProtobufVarint32LengthFieldPrepender：向 ByteBuf 前追加一个 Google Protocol Buffers 的 "Base 128
     * Varints" 整型的长度字段值。
     *
     * PS：关于 Base 128 Varints，可参见 Google 的 Protocol Buffers 编码的开发者指南：
     * https://developers.google.com/protocol-buffers/docs/encoding
     *
     * 以 ProtoBufInitializer 为例，在这里又看到了，使用 protobuf 只不过是将正确的 ChannelHandler 添加到
     * ChannelPipeline 中。
     *
     * PS：这里需要引入 Protocol Buffers 的 jar 包：com.google.protobuf » protobuf-java » 2.6.1
     *
     *
     *
     * 在这里，探讨了由 Netty 专门的解码器和编码器所支持的不同的序列化选项：
     * （1）标准 JDK 序列化
     * （2）JBoss Marshalling
     * （3）Google 的 Protocol Buffers
     */
    public static void main(String[] args) {

    }

}
