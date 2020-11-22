package com.siwuxie095.network.chapter8th.example9th;

/**
 * @author Jiajing Li
 * @date 2020-11-22 17:57:30
 */
public class Main {

    /**
     * 关闭
     *
     * 引导使你的应用程序启动并且运行起来，但是迟早你都需要优雅地将它关闭。当然，你也可以让 JVM 在退出时处理好一切，
     * 但是这不符合优雅的定义，优雅是指干净地释放资源。关闭 Netty 应用程序并没有太多的魔法，但是还是有些事情需要记
     * 在心上。
     *
     * 最重要的是，你需要关闭 EventLoopGroup，它将处理任何挂起的事件和任务，并且随后释放所有活动的线程。这就是调
     * 用 EventLoopGroup.shutdownGracefully() 方法的作用。 这个方法调用将会返回一个 Future，这个 Future 将
     * 在关闭完成时接收到通知。需要注意的是，shutdownGracefully() 方法也是一个异步的操作，所以你需要阻塞等待直到
     * 它完成，或者向所返回的 Future 注册一个监听器以在关闭完成时获得通知。
     *
     * 以 GracefulShutdown 为例，它符合了优雅关闭的定义。
     *
     * 或者，你也可以在调用 EventLoopGroup.shutdownGracefully() 方法之前，显式地在所有活动的 Channel 上调用
     * Channel.close() 方法。但是在任何情况下，都请记得关闭 EventLoopGroup 本身。
     */
    public static void main(String[] args) {

    }

}
