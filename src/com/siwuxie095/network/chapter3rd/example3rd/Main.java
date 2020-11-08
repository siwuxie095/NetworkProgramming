package com.siwuxie095.network.chapter3rd.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-11-08 19:59:03
 */
public class Main {

    /**
     * ChannelHandler 和 ChannelPipeline
     *
     * 下面将更加细致地看一看那些管理数据流以及执行应用程序处理逻辑的组件。
     * （1）ChannelHandler：执行应用程序处理逻辑；
     * （2）ChannelPipeline：管理数据流。
     *
     *
     *
     * ChannelHandler 接口
     *
     * 从应用程序开发人员的角度来看，Netty 的主要组件是 ChannelHandler，它充当了所有处理入站和出站数据的应用程序
     * 逻辑的容器。这是可行的，因为 ChannelHandler 的方法是由网络事件（其中术语 "事件" 的使用非常广泛）触发的。
     *
     * 事实上，ChannelHandler 可专门用于几乎任何类型的动作，例如将数据从一种格式转换为另外一种格式，或者处理转换
     * 过程中所抛出的异常。
     *
     * 举例来说，ChannelInboundHandler 是一个会经常实现的子接口。这种类型的 ChannelHandler 接收入站事件和数据，
     * 这些数据随后将会被应用程序的业务逻辑所处理。当要给连接的客户端发送响应时，也可以从 ChannelInboundHandler
     * 冲刷数据。应用程序的业务逻辑通常驻留在一个或者多个 ChannelInboundHandler 中。
     *
     *
     *
     * ChannelPipeline 接口
     *
     * ChannelPipeline 提供了 ChannelHandler 链的容器，并定义了用于在该链上传播入站和出站事件流的 API。
     *
     * 当 Channel 被创建时，它会被自动地分配到它专属的 ChannelPipeline。
     *
     * ChannelHandler 安装到 ChannelPipeline 中的过程如下所示：
     * （1）一个 ChannelInitializer 的实现被注册到了 Bootstrap（客户端 Bootstrap）或 ServerBootstrap
     * （服务器 Bootstrap）中。
     * （2）当 ChannelInitializer.initChannel() 方法被调用时，ChannelInitializer 将在 ChannelPipeline
     * 中安装一组自定义的 ChannelHandler。
     * （3）ChannelInitializer 将它自己从 ChannelPipeline 中移除。
     *
     * 为了审查发送或者接收数据时将会发生什么，不妨更加深入地研究一下 ChannelPipeline 和 ChannelHandler 之间
     * 的共生关系。
     *
     * ChannelHandler 是专为支持广泛的用途而设计的，可以将它看作是处理往来 ChannelPipeline 事件（包括数据）
     * 的任何代码的通用容器。如下继承结构可以说明这一点：
     * （1）ChannelInboundHandler 接口继承自 ChannelHandler 接口。
     * （2）ChannelOutboundHandler 接口继承自 ChannelHandler 接口。
     *
     * 使得事件流经 ChannelPipeline 是 ChannelHandler 的工作，它们是在应用程序的初始化或者引导阶段被安装的。
     * 这些对象接收事件、执行它们所实现的处理逻辑，并将数据传递给链中的下一个 ChannelHandler。它们的执行顺序是
     * 由它们被添加的顺序所决定的。实际上，被称为 ChannelPipeline 的是这些 ChannelHandler 的编排顺序。
     *
     * Netty 应用程序中入站和出站数据流之间的区别：从一个客户端应用程序的角度来看，如果事件的运动方向是从客户端
     * 到服务器端，就称这些事件为出站的，反之则称为入站的。
     *
     * 入站和出站 ChannelHandler 可以被安装到同一个 ChannelPipeline 中。如果一个消息或者任何其他的入站事件
     * 被读取，那么它会从 ChannelPipeline 的头部开始流动，并被传递给第一个 ChannelInboundHandler。这个
     * ChannelHandler 不一定会实际地修改数据，具体取决于它的具体功能，在这之后，数据将会被传递给链中的下一个
     * ChannelInboundHandler。最终，数据将会到达 ChannelPipeline 的尾端，届时，所有处理就都结束了。
     *
     * 数据的出站运动（即正在被写的数据）在概念上也是一样的。在这种情况下，数据将从 ChannelOutboundHandler
     * 链的尾端开始流动，直到它到达链的头部为止。在这之后，出站数据将会到达网络传输层，即 Socket 处。通常情况下，
     * 这将触发一个写操作。
     *
     *
     * 关于入站和出站 ChannelHandler 的更多讨论：
     *
     * 通过使用作为参数传递到每个方法的 ChannelHandlerContext，事件可以被传递给当前 ChannelHandler 链中的
     * 下一个 ChannelHandler。
     *
     * 因为有时会忽略那些不感兴趣的事件，所以 Netty 提供了抽象基类 ChannelInboundHandlerAdapter 和
     * ChannelOutboundHandlerAdapter。通过调用 ChannelHandlerContext 上的对应方法，每个对应方法都提供了
     * 简单地将事件传递给下一个 ChannelHandler 的实现。随后，可以通过重写所感兴趣的那些方法来扩展这些类。
     *
     *
     * 鉴于出站操作和入站操作是不同的，那么将两个类别的 ChannelHandler 都混合添加到同一个 ChannelPipeline
     * 中会发生什么呢？虽然 ChannelInboundHandler 和 ChannelOutboundHandler 都扩展自 ChannelHandler，
     * 但是 Netty 能区分 ChannelInboundHandler 实现和 ChannelOutboundHandler 实现，并确保数据只会在具有
     * 相同定向类型的两个 ChannelHandler 之间传递。
     *
     * 当 ChannelHandler 被添加到 ChannelPipeline 时，它将会被分配一个 ChannelHandlerContext，其代表了
     * ChannelHandler 和 ChannelPipeline 之间的绑定。虽然这个对象可以被用于获取底层的 Channel，但是它主要
     * 还是被用于写出站数据。
     *
     * 在 Netty 中，有两种发送消息的方式：
     * （1）可以直接写到Channel中；
     * （2）也可以写到和 ChannelHandler 相关联的 ChannelHandlerContext 对象中。
     *
     * 前一种方式将会导致消息从 ChannelPipeline 的尾端开始流动，而后者将导致消息从 ChannelPipeline 中的下
     * 一个 ChannelHandler 开始流动。
     *
     *
     *
     * 更加深入地了解 ChannelHandler
     *
     * ChannelHandler 有许多不同类型，它们各自的功能主要取决于它们的超类。Netty 以适配器类的形式提供了大量
     * 默认的 ChannelHandler 实现，其旨在简化应用程序处理逻辑的开发过程。已经知道，ChannelPipeline 中的
     * 每个 ChannelHandler 将负责把事件转发到链中的下一个 ChannelHandler。而这些适配器类（及它们的子类）
     * 将自动执行这个操作，所以可以只重写那些想要特殊处理的方法和事件。
     *
     * 为什么需要适配器类：
     *
     * 有一些适配器类可以将编写自定义的 ChannelHandler 所需要的努力降到最低限度，因为它们提供了定义在对应
     * 接口中的所有方法的默认实现。下面这些是编写自定义 ChannelHandler 时经常会用到的适配器类：
     * （1）ChannelHandlerAdapter
     * （2）ChannelInboundHandlerAdapter
     * （3）ChannelOutboundHandlerAdapter
     * （4）ChannelDuplexHandler
     *
     *
     * 接下来将研究 3 个 ChannelHandler 的子类型：
     * （1）编码器；
     * （2）解码器；
     * （3）SimpleChannelInboundHandler<T>（ChannelInboundHandlerAdapter 的一个子类）。
     *
     *
     *
     * 编码器和解码器
     *
     * 当通过 Netty 发送或者接收一个消息的时候，就将会发生一次数据转换。入站消息会被解码；也就是说，从字节转换
     * 为另一种格式，通常是一个 Java 对象。如果是出站消息，则会发生相反方向的转换：它将从它的当前格式被编码为
     * 字节。这两种方向的转换的原因很简单：网络数据总是一系列的字节。
     *
     * 对应于特定的需要，Netty 为编码器和解码器提供了不同类型的抽象类。例如，你的应用程序可能使用了一种中间格式，
     * 而不需要立即将消息转换成字节。你将仍然需要一个编码器，但是它将派生自一个不同的超类。为了确定合适的编码器
     * 类型，可以应用一个简单的命名约定。
     *
     * 通常来说，这些基类的名称将类似于 ByteToMessageDecoder 或 MessageToByteEncoder。对于特殊的类型，则
     * 类似于 ProtobufEncoder 和 ProtobufDecoder 这样的名称（预置的用来支持 Google 的 Protocol Buffers）。
     *
     * 严格地说，其他的处理器也可以完成编码器和解码器的功能。但是，和用来简化 ChannelHandler 的创建的适配器类
     * 一样，所有由 Netty 提供的编码器/解码器适配器类都实现了 ChannelInboundHandler 或 ChannelOutboundHandler
     * 接口。
     *
     * 不难发现，对于入站数据来说，channelRead 方法/事件已经被重写了。对于每个从入站 Channel 读取的消息，这个
     * 方法都将会被调用。随后，它将调用由预置解码器所提供的 decode() 方法，并将已解码的字节转发给 ChannelPipeline
     * 中的下一个 ChannelInboundHandler。
     *
     * 出站消息的模式是相反方向的：编码器将消息转换为字节，并将它们转发给下一个 ChannelOutboundHandler。
     *
     *
     *
     * 抽象类 SimpleChannelInboundHandler
     *
     * 最常见的情况是，你的应用程序会利用一个 ChannelHandler 来接收解码消息，并对该数据应用业务逻辑。要创建一个
     * 这样的 ChannelHandler，只需要扩展基类 SimpleChannelInboundHandler<T>，其中 T 是要处理的消息的 Java
     * 类型。在这个 ChannelHandler 中，将需要重写基类的一个或者多个方法，并且获取一个到 ChannelHandlerContext
     * 的引用，这个引用将作为输入参数传递给 ChannelHandler 的所有方法。
     *
     * 在这种类型的 ChannelHandler 中，最重要的方法是 channelRead0(ChannelHandlerContext,T)。除了要求不要
     * 阻塞当前的 I/O 线程之外，其具体实现完全取决于你。
     */
    public static void main(String[] args) {

    }

}
