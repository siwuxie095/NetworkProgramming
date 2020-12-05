package com.siwuxie095.network.chapter15th.example3rd;

/**
 * @author Jiajing Li
 * @date 2020-12-05 18:38:01
 */
public class Main {

    /**
     * 什么是 Thrift
     *
     * Thrift 是一个用来构建服务和客户端的框架，其通过远程过程调用（RPC）来进行通信。 它最初是在 Facebook 开发的，
     * 用以满足我们构建能够处理客户端和服务器之间的特定类型的接口不匹配的服务的需要。这种方式十分便捷，因为服务器和
     * 它们的客户端通常不能全部同时升级。
     *
     * PS：一份来自原始的 Thrift 的开发者的不旧不新的白皮书可以参见：
     * https://thrift.apache.org/static/files/thrift-20070401.pdf
     *
     * Thrift 的另一个重要的特点是它可以被用于多种语言。这使得在 Facebook 的团队可以为工作选择正确的语言，而不必
     * 担心他们是否能够找到和其他的服务相互交互的客户端代码。在 Facebook，Thrift 已经成为我们的后端服务之间相互
     * 通信的主要方式之一，同时它还被用于非 RPC 的序列化任务，因为它提供了一个通用的、紧凑的存储格式，能够被多种语
     * 言读取，以便后续处理。
     *
     * 自从 Thrift 在 Facebook 被开发以来，它已经作为一个 Apache 项目（https://thrift.apache.org/）开源了，
     * 在那里它将继续成长以满足服务开发人员的需要，不止在 Facebook 有使用，在其他公司也有使用，如 Evernote 和
     * last.fm，以及主要的开源项目如 Apache Cassandra 和 HBase 等。
     *
     * 下面是 Thrift 的主要组件：
     * （1）Thrift 的接口定义语言（IDL）—— 用来定义你的服务，并且编排你的服务将要发送和接收的任何自定义类型；
     * （2）协议 —— 用来控制将数据元素编码/解码为一个通用的二进制格式（如 Thrift 的二进制协议或者 JSON）；
     * （3）传输 —— 提供了一个用于读/写不同媒体（如 TCP 套接字、管道、内存缓冲区）的通用接口；
     * （4）Thrift 编译器 —— 解析 Thrift 的 IDL 文件以生成用于服务器和客户端的存根代码，以及在 IDL 中定义的自
     * 定义类型的序列化/反序列化代码；
     * （5）服务器实现 —— 处理接受连接、从这些连接中读取请求、派发调用到实现了这些接口的对象，以及将响应发回给客户
     * 端；
     * （6）客户端实现 —— 将方法调用转换为请求，并将它们发送给服务器。
     */
    public static void main(String[] args) {

    }

}
