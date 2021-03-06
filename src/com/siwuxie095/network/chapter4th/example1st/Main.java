package com.siwuxie095.network.chapter4th.example1st;

/**
 * @author Jiajing Li
 * @date 2020-11-09 07:21:43
 */
public class Main {

    /**
     * 传输
     *
     * 流经网络的数据总是具有相同的类型：字节。这些字节是如何流动的主要取决于网络传输，一个有助于抽象底层数据传输机制的概念。
     * 用户并不关心这些细节，他们只想确保他们的字节被可靠地发送和接收。
     *
     * 如果你有 Java 网络编程的经验，那么你可能已经发现，在某些时候，你需要支撑比预期多很多的并发连接。如果你随后尝试从阻塞
     * 传输切换到非阻塞传输，那么你可能会因为这两种网络 API 的截然不同而遇到问题。
     *
     * 然而，Netty 为它所有的传输实现提供了一个通用 API，这使得这种转换比你直接使用 JDK 所能够达到的简单得多。所产生的代码
     * 不会被实现的细节所污染，而你也不需要在你的整个代码库上进行广泛的重构。简而言之，你可以将时间花在其他更有成效的事情上。
     *
     * 这里将学习该通用 API，并通过和 JDK 的对比来证明它极其简单易用。同时也会阐述 Netty 自带的不同传输实现，以及它们各自
     * 适用的场景。有了这些信息之后，你会发现选择最适合于你的应用程序的选项将是直截了当的。
     */
    public static void main(String[] args) {

    }

}
