package com.siwuxie095.network.chapter8th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-22 16:37:36
 */
public class Main {

    /**
     * 从 Channel 引导客户端
     *
     * 假设你的服务器正在处理一个客户端的请求，这个请求需要它充当第三方系统的客户端。当一个应用程序（如一个代理服务器）
     * 必须要和组织现有的系统（如 Web 服务或者数据库）集成时，就可能发生这种情况。在这种情况下，将需要从已经被接受的
     * 子 Channel 中引导一个客户端 Channel。
     *
     * 此时可以创建新的 Bootstrap 实例，但是这并不是最高效的解决方案，因为它将要求你为每个新创建的客户端 Channel
     * 定义另一个 EventLoop。这会产生额外的线程，以及在已被接受的子 Channel 和客户端 Channel 之间交换数据时不可
     * 避免的上下文切换。
     *
     * 一个更好的解决方案是：通过将已被接受的子 Channel 的 EventLoop 传递给 Bootstrap 的 group() 方法来共享该
     * EventLoop。因为分配给 EventLoop 的所有 Channel 都使用同一个线程，所以这避免了额外的线程创建，以及前面所
     * 提到的相关的上下文切换。这个共享的解决方案如下：
     * （1）在 bind() 方法被调用时，ServerBootstrap 将创建一个新的 ServerChannel。
     * （2）ServerChannel 接受新的连接，并创建子 Channel 来处理它们。
     * （3）为已被接受的连接创建子 Channel。
     * （4）由子 Channel 创建的 Bootstrap 类的实例将在 connect() 方法被调用时创建新的 Channel。
     * （5）新的 Channel 连接到了远程节点。
     * （6）EventLoop 在由 ServerChannel 所创建子 Channel 以及由 connect() 方法所创建 Channel 之间共享。
     *
     * 实现 EventLoop 共享涉及通过调用 group()方法来设置 EventLoop，以 BootstrapSharingEventLoopGroup 为例。
     *
     * 这里的解决方案反映了编写 Netty 应用程序的一般准则：尽可能地重用 EventLoop，以减少线程创建所带来的开销。
     */
    public static void main(String[] args) {

    }

}
