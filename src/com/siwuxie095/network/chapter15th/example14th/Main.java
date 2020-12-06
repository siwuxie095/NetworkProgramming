package com.siwuxie095.network.chapter15th.example14th;

/**
 * @author Jiajing Li
 * @date 2020-12-06 10:53:28
 */
public class Main {

    /**
     * Finagle 的抽象
     *
     * Finagle 的核心概念是一个从 Request 到 Future[Response] 的的简单函数（函数式编程语言是这里的关键）。
     *
     * PS：这里的 Future[Response] 相当于 Java 8 中的 CompletionStage<Response>。
     *
     * 这种简单性释放了非常强大的组合性。Service 是一种对称的 API，同时代表了客户端以及服务器。服务器实现了
     * 该服务的接口。该服务器可以被具体地用于测试，或者 Finagle 也可以将它暴露到网络接口上。客户端将被提供一
     * 个服务实现，其要么是虚拟的，要么是某个远程服务器的具体表示。
     *
     * 例如，可以通过实现一个服务来创建一个简单的 HTTP 服务器，该服务接受 HttpReq 作为参数，返回一个代表最
     * 终响应的 Future[HttpRep]。
     *
     * 随后，客户端将被提供一个该服务的对称表示。
     *
     * 这个例子将把该服务器暴露到所有网络接口的 80 端口上，并从 twitter.com 的 80 端口消费。 也可以选择不
     * 暴露该服务器，而是直接使用它。
     *
     * 在这里，客户端代码有相同的行为，只是不需要网络连接。这使得测试客户端和服务器非常简单直接。
     *
     * 客户端以及服务器都提供了特定于应用程序的功能。但是，也有对和应用程序无关的功能的需求。这样的例子如超时、
     * 身份验证以及统计等。Filter 为实现应用程序无关的功能提供了抽象。
     *
     * 过滤器接收一个请求和一个将被它组合的服务。多个过滤器可以在被应用到某个服务之前链接在一起。
     *
     * 这允许了清晰的逻辑抽象以及良好的关注点分离。在内部，Finagle 大量地使用了过滤器，其有助于提高模块化以
     * 及可复用性。它们已经被证明，在测试中很有价值，因为它们通过很小的模拟便可以被独立地单元测试。
     *
     * 过滤器可以同时修改请求和响应的数据以及类型。
     *
     * 另外：部分摘要代码，可参考 PDF。
     */
    public static void main(String[] args) {

    }

}