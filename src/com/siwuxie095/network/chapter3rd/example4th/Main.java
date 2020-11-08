package com.siwuxie095.network.chapter3rd.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-08 21:11:32
 */
public class Main {

    /**
     * 引导
     *
     * Netty 的引导类为应用程序的网络层配置提供了容器，这涉及将一个进程绑定到某个指定的端口，或者将一个进程
     * 连接到另一个运行在某个指定主机的指定端口上的进程。
     *
     * 通常来说，把前面的用例称作引导一个服务器，把后面的用例称作引导一个客户端。虽然这个术语简单方便，但是它
     * 略微掩盖了一个重要的事实，即 "服务器" 和 "客户端" 实际上表示了不同的网络行为。换句话说，是监听传入的
     * 连接还是建立到一个或者多个进程的连接。
     *
     * PS：面向连接的协议。请记住，严格来说，"连接" 这个术语仅适用于面向连接的协议，如 TCP，其保证了两个连接
     * 端点之间消息的有序传递。
     *
     * 因此，有两种类型的引导：
     * （1）一种用于客户端（即 Bootstrap）；
     * （2）另一种用于服务器（即 ServerBootstrap）。
     *
     * 无论你的应用程序使用哪种协议或者处理哪种类型的数据，唯一决定它使用哪种引导类的是它是作为一个客户端还是
     * 作为一个服务器。
     *
     * Bootstrap 和 ServerBootstrap 的区别：
     * （1）网络编程中的作用：Bootstrap 是连接到远程主机和端口，ServerBootstrap 是绑定到一个本地端口。
     * （2）EventLoopGroup 的数目：Bootstrap 是 1，ServerBootstrap 是 2。
     *
     * 对于第一个区别：ServerBootstrap 将绑定到一个端口，因为服务器必须要监听连接，而 Bootstrap 则是由想
     * 要连接到远程节点的客户端应用程序所使用的。
     *
     * 对于第二个区别：引导一个客户端只需要一个 EventLoopGroup，但是一个 ServerBootstrap 则需要两个，这
     * 是为什么呢？
     *
     * 因为服务器需要两组不同的 Channel。第一组将只包含一个 ServerChannel，代表服务器自身的已绑定到某个本
     * 地端口的正在监听的套接字。而第二组将包含所有已创建的用来处理传入客户端连接（对于每个服务器已经接受的连
     * 接都有一个）的 Channel。
     *
     * 与 ServerChannel 相关联的 EventLoopGroup 将分配一个负责为传入连接请求创建 Channel 的 EventLoop。
     * 一旦连接被接受，第二个 EventLoopGroup 就会给它的 Channel 分配一个 EventLoop。
     *
     * PS：实际上，ServerBootstrap 类也可以只使用一个 EventLoopGroup，此时其将在两个场景下共用同一个
     * EventLoopGroup。
     */
    public static void main(String[] args) {

    }

}
