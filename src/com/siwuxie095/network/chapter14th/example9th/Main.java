package com.siwuxie095.network.chapter14th.example9th;

/**
 * @author Jiajing Li
 * @date 2020-12-05 09:27:15
 */
public class Main {

    /**
     * Firebase：实时的数据同步服务
     *
     * Sara Robinson，Developer Happiness 副总裁
     * Greg Soltis，Cloud Architecture 副总裁
     *
     * 实时更新是现代应用程序中用户体验的一个组成部分。随着用户期望这样的行为，越来越多的应用程序都正在实时地
     * 向用户推送数据的变化。通过传统的 3 层架构很难实现实时的数据同步，其需要开发者管理他们自己的运维、服务
     * 器以及伸缩。通过维护到客户端的实时的、双向的通信，Firebase 提供了一种即时的直观体验，允许开发人员在几
     * 分钟之内跨越不同的客户端进行应用程序数据的同步 — 这一切都不需要任何的后端工作、服务器、运维或者伸缩。
     *
     * 实现这种能力提出了一项艰难的技术挑战，而 Netty 则是用于在 Firebase 内构建用于所有网络通信的底层框架
     * 的最佳解决方案。这个案例研究概述了 Firebase 的架构，然后审查了 Firebase 使用 Netty 以支撑它的实时
     * 数据同步服务的 3 种方式：
     * （1）长轮询；
     * （2）HTTP 1.1 keep-alive 和流水线化；
     * （3）控制 SSL 处理器。
     */
    public static void main(String[] args) {

    }

}
