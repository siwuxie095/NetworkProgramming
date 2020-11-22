package com.siwuxie095.network.chapter9th.example1st;

/**
 * @author Jiajing Li
 * @date 2020-11-22 18:39:33
 */
public class Main {

    /**
     * 单元测试
     *
     * ChannelHandler 是 Netty 应用程序的关键元素，所以彻底地测试它们应该是开发过程的一个标准部分。最佳实践要求
     * 测试不仅要能够证明你的实现是正确的，而且还要能够很容易地隔离那些因修改代码而突然出现的问题。这种类型的测试叫
     * 作单元测试。
     *
     * 虽然单元测试没有统一的定义，但是大多数的从业者都有基本的共识。其基本思想是，以尽可能小的区块测试你的代码，并
     * 且尽可能地和其他的代码模块以及运行时的依赖（如数据库和网络）相隔离。如果你的应用程序能通过测试验证每个单元本
     * 身都能够正常地工作，那么在出了问题时将可以更加容易地找出根本原因。
     *
     * 这里将学习一种特殊的 Channel 实现：EmbeddedChannel，它是 Netty 专门为改进针对 ChannelHandler 的单元
     * 测试而提供的。
     *
     * 因为正在被测试的代码模块或者单元将在它正常的运行时环境之外被执行，所以你需要一个框架或者脚手架以便在其中运行
     * 它。这里将使用 JUnit 4 作为测试框架。
     *
     * PS：共需引入两个单元测试相关的 jar 包，如下：
     * （1）junit » junit » 4.13
     * （2）org.hamcrest » hamcrest-core » 1.3
     */
    public static void main(String[] args) {

    }

}
