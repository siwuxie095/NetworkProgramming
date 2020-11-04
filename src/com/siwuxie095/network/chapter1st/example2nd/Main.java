package com.siwuxie095.network.chapter1st.example2nd;

import java.io.IOException;

/**
 * @author Jiajing Li
 * @date 2020-11-04 08:21:13
 */
@SuppressWarnings("all")
public class Main {

    /**
     * Java 网络编程
     *
     * 早期的网络编程开发人员，需要花费大量的时间去学习复杂的 C 语言套接字库，去处理它们在不同操作系统上出现的古怪问题。
     * 虽然最早的 Java（1995—2002）引入了足够多的面向对象 façade（门面）来隐藏一些棘手的细节问题，但是创建一个复杂的
     * 客户端/服务器协议仍然需要大量的样板代码，以及相当多的底层研究，才能使它整个流畅地运行起来。
     *
     * 那些最早期的 Java API（java.net）只支持由本地系统套接字库提供的所谓的阻塞函数。
     *
     * 以 BlockingIoExample 为例，展示了一个使用了这些函数调用的服务器代码和客户端代码。
     *
     * 其中实现了 Socket API 的基本模式之一。以下是最重要的几点。
     *
     * （1）ServerSocket 上的 accept() 方法将会一直阻塞到一个连接建立，随后返回一个新的 Socket 用于客户端和服务器
     * 之间的通信。该 ServerSocket 将继续监听传入的连接。
     * （2）BufferedReader 和 PrintWriter 都衍生自 Socket 的输入输出流。前者从一个字符输入流中读取文本，后者打印
     * 对象的格式化的表示到文本输出流。
     * （3）readLine() 方法将会阻塞，直到一个由换行符或回车符结尾的字符串被读取。
     * （4）processRequest() 方法调用后，客户端的请求已经被处理。
     *
     * 这段代码片段将只能同时处理一个连接，要管理多个并发客户端，需要为每个新的客户端 Socket 创建一个新的 Thread。
     *
     * 这种方案的影响如下：
     * （1）第一，在任何时候都可能有大量的线程处于休眠状态，只是等待输入或输出数据就绪，这可能算是一种资源浪费。
     * （2）第二，需要为每个线程的调用栈都分配内存，其默认值大小区间为 64 KB 到 1 MB，具体取决于操作系统。
     * （3）第三，即使 Java 虚拟机（JVM）在物理上可以支持非常大数量的线程，但是远在到达该极限之前，上下文切换所带来的
     * 开销就会带来麻烦，例如，在达到 10 000 个连接的时候。
     *
     * 虽然这种并发方案对于支撑中小数量的客户端来说还算可以接受，但是为了支撑 100 000 或更多的并发连接所需要的资源使
     * 得它很不理想。幸运的是，还有一种方案。
     *
     *
     *
     * Java NIO
     *
     * 除了上例中代码底层的阻塞系统调用之外，本地套接字库很早就提供了非阻塞调用，其为网络资源的利用率提供了相当多的控制：
     * （1）可以使用 setsockopt() 方法配置套接字，以便读/写调用在没有数据的时候立即返回，也就是说，如果是一个阻塞调用
     * 应该已经被阻塞了（注意：setsockopt() 是 C 中的方法，对应到 Java 中是 Socket 的 setOption() 方法。参考链接：
     * https://docs.oracle.com/javase/8/docs/technotes/guides/net/socketOpt.html）；
     * （2）可以使用操作系统的事件通知 API 注册一组非阻塞套接字，以确定它们中是否有任何的套接字已经有数据可供读写。
     *
     * Java 对于非阻塞 I/O 的支持是在 2002 年引入的，位于 JDK 1.4 的 java.nio 包中。
     *
     * PS：NIO 最开始是新的输入/输出（New Input/Output）的英文缩写，但是，该 Java API 已经出现足够长的时间了，不再
     * 是 "新的" 了，因此，如今大多数用户认为 NIO 代表非阻塞 I/O（Non-blocking I/O），而阻塞 I/O（blocking I/O）
     * 是旧的输入/输出（old input/output，OIO）。也可能会遇到它被称为普通 I/O（plain I/O）的时候。
     *
     *
     *
     * 选择器 Selector
     *
     * class java.nio.channels.Selector 是 Java 的非阻塞 I/O 实现的关键。它使用了事件通知 API 以确定在一组非阻塞
     * 套接字中有哪些已经就绪能够进行 I/O 相关的操作。因为可以在任何时间检查任意的读操作或写操作的完成状态。也就是说，一
     * 个单一的线程便可以处理多个并发的连接。
     *
     * 总体来看，与阻塞 I/O 模型相比，这种模型提供了更好的资源管理：
     * （1）使用较少的线程便可以处理许多连接，因此也减少了内存管理和上下文切换所带来开销；
     * （2）当没有 I/O 操作需要处理的时候，线程也可以被用于其他任务。
     *
     * 尽管已经有许多直接使用 Java NIO API 的应用程序被构建了，但是要做到如此正确和安全并不容易。特别是，在高负载下可靠
     * 和高效地处理和调度 I/O 操作是一项繁琐而且容易出错的任务，这项任务最好留给高性能的网络编程专家 —— Netty。
     */
    public static void main(String[] args) throws InterruptedException {
        int portNumber = 8000;
        BlockingIoExample example = new BlockingIoExample();
        new Thread(() -> {
            try {
                example.serve(portNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        new Thread(() -> {
            try {
                example.client(portNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
