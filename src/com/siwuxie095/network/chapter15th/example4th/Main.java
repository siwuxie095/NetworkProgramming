package com.siwuxie095.network.chapter15th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 18:52:08
 */
public class Main {

    /**
     * 使用 Netty 改善 Java Thrift 的现状
     *
     * Thrift 的 Apache 分发版本已经被移植到了大约 20 种不同的语言，而且还有用于其他语言的和 Thrift 相互兼容的
     * 独立框架（Twitter 的用于 Scala 的 Finagle 便是一个很好的例子）。这些语言中的一些在 Facebook 多多少少有
     * 被使用，但是在 Facebook 最常用的用来编写 Thrift 服务的还是 C++ 和 Java。
     *
     * 当他加入 Facebook 时，他们已经在使用 C++ 围绕着 libevent，顺利地开发可靠的、高性能的、异步的 Thrift 实
     * 现了。通过 libevent，他们得到了 OS API 之上的跨平台的异步 I/O 抽象，但是 libevent 并不会比，比如说，原
     * 始的 Java NIO，更加容易使用。因此，他们也在其上构建了抽象，如异步的消息通道，同时他们还使用了来自 Folly
     * 的链式缓冲区尽可能地避免复制。这个框架还具有一个支持带有多路复用的异步调用的客户端实现，以及一个支持异步的请
     * 求处理的服务器实现（该服务器可以启动一个异步任务来处理请求并立即返回，随后在响应就绪时调用一个回调或者稍后设
     * 置一个 Future）。
     *
     * PS：Folly 是 Facebook 的开源 C++ 公共库：
     * https://www.facebook.com/notes/facebook-engineering/folly-the-facebook-open-source-library/10150864656793920
     *
     * 同时，他们的 Java Thrift 框架却很少受到关注，而且他们的负载测试工具显示 Java 版本的性能明显落后于 C++ 版
     * 本。虽然已经有了构建于 NIO 之上的 Java Thrift 框架，并且异步的基于 NIO 的客户端也可用。但是该客户端不支持
     * 流水线化以及请求的多路复用，而服务器也不支持异步的请求处理。由于这些缺失的特性，在 Facebook，这里的 Java
     * Thrift 服务开发人员遇到了那些在 C++（的 Thrift 框架）中已经解决了的问题，并且它也成为了挫败感的源泉。
     *
     * 他们本来可以在 NIO 之上构建一个类似的自定义框架，并在那之上构建他们新的 Java Thrift 实现，就如同他们为 C++
     * 版本的实现所做的一样。但是经验告诉他们，这需要巨大的工作量才能完成，不过碰巧，他们所需要的框架已经存在了，只
     * 等着他们去使用它：Netty。
     *
     * 他们很快地组装了一个服务器实现，并且将名字 "Netty" 和 "Thrift" 混在一起，为新的服务器实现提出了 "Nifty"
     * 这个名字。相对于在 C++版本中达到同样的效果他们所需要做的一切，那么少的代码便可以使得 Nifty 工作，这立即就让
     * 人印象深刻。
     *
     * 接下来，他们使用 Nifty 构建了一个简单的用于负载测试的 Thrift 服务器，并且使用他们的负载测试工具，将它和他们
     * 现有的服务器进行了对比。结果是显而易见的：Nifty 的表现要优于其他的 NIO 服务器，而且和他们最新的 C++版本的
     * Thrift 服务器的结果也不差上下。使用 Netty 就是为了要提高性能！
     */
    public static void main(String[] args) {

    }

}
