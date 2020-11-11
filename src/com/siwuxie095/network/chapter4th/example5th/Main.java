package com.siwuxie095.network.chapter4th.example5th;

/**
 * @author Jiajing Li
 * @date 2020-11-11 08:24:45
 */
public class Main {

    /**
     * 传输的用例
     *
     * 并不是所有的传输都支持所有的核心协议，这就可能会限制你的选择。所以需要考虑一下选用一个传输以适用于特定用途的协议
     * 的影响因素。如下是截止到目前（2020/11/11）支持的传输和网络协议。
     *
     * 传输                       TCP         UDP     SCTP        UDT
     * NIO                        X           X       X           X
     * Epoll（仅 Linux）           X           X       -           -
     * OIO                        X           X       X           X
     *
     * 关于 SCTP：
     *
     * 参见 RFC 2960 中有关流控制传输协议（SCTP）的解释：https://www.ietf.org/rfc/rfc2960.txt
     *
     * 在 Linux 上启用 SCTP：
     * SCTP 需要内核的支持，并且需要安装用户库。
     *
     * 例如，对于 Ubuntu，可以使用下面的命令：
     * # sudo apt-get install libsctp1
     *
     * 对于 Fedora，可以使用 yum：
     * #sudo yum install kernel-modules-extra.x86_64 lksctp-tools.x86_64
     *
     * 有关如何启用 SCTP 的详细信息，请参考你的 Linux 发行版的文档。
     *
     *
     * 关于 UDT：
     *
     * UDT 协议实现了基于 UDP 协议的可靠传输，详见 https://zh.wikipedia.org/zh-cn/UDT
     *
     *
     * 虽然只有 SCTP 传输有这些特殊要求，但是其他传输可能也有它们自己的配置选项需要考虑。此外，如果只是为了支持更高的
     * 并发连接数，服务器平台可能需要配置得和客户端不一样。
     *
     * 下面是一些很可能会遇到的用例：
     *
     * （1）非阻塞代码库：
     * 如果你的代码库中没有阻塞调用（或者你能够限制它们的范围），那么在 Linux 上使用 NIO 或者 Epoll 始终是个好主意。
     * 虽然 NIO/Epoll 旨在处理大量的并发连接，但是在处理较小数目的并发连接时，它也能很好地工作，尤其是考虑到它在连接
     * 之间共享线程的方式。
     *
     * （2）阻塞代码库：
     * 如果你的代码库严重地依赖于阻塞 I/O，而且你的应用程序也有一个相应的设计，那么在尝试将其直接转换为 Netty 的 NIO
     * 传输时，可能会遇到和阻塞操作相关的问题。不要为此而重写你的代码，可以考虑分阶段迁移：先从 OIO 开始，等你的代码
     * 修改好之后，再迁移到 NIO（或者使用 Epoll，如果你在使用 Linux）。
     *
     * （3）在同一个 JVM 内部的通信：
     * 在同一个 JVM 内部的通信，不需要通过网络暴露服务，是 Local 传输的完美用例。这将消除所有真实网络操作的开销，
     * 同时仍然使用你的 Netty 代码库。如果随后需要通过网络暴露服务，那么只需要把传输改为 NIO 或者 OIO 即可。
     *
     * （4）测试你的 ChannelHandler 实现：
     * 如果你想要为自己的 ChannelHandler 实现编写单元测试，那么请考虑使用 Embedded 传输。这既便于测试你的代码，
     * 而又不需要创建大量的模拟（mock）对象。你的类将仍然符合常规的 API 事件流，保证该 ChannelHandler 在和真实
     * 的传输一起使用时能够正确地工作。
     *
     *
     * 针对以上用例，应用程序的最佳传输如下：
     * （1）非阻塞代码库或者一个常规的起点：推荐 NIO（或者在 Linux 上使用 Epoll）；
     * （2）阻塞代码库：推荐 OIO；
     * （3）在同一个 JVM 内部的通信：推荐 Local；
     * （4）测试 ChannelHandler 的实现：推荐 Embedded。
     */
    public static void main(String[] args) {

    }

}
