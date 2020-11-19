package com.siwuxie095.network.chapter7th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-19 22:25:27
 */
public class Main {

    /**
     * 任务调度
     *
     * 偶尔，你将需要调度一个任务以便稍后（延迟）执行或者周期性地执行。例如，你可能想要注册一个在客户端已经
     * 连接了 5 分钟之后触发的任务。一个常见的用例是，发送心跳消息到远程节点，以检查连接是否仍然还活着。如
     * 果没有响应，你便知道可以关闭该 Channel 了。
     *
     * 下面将展示如何使用核心的 Java API 和 Netty 的 EventLoop 来调度任务。然后，将研究 Netty 的内部
     * 实现，并讨论它的优点和局限性。
     *
     *
     *
     * JDK 的任务调度 API
     *
     * 在 Java 5 之前，任务调度是建立在 java.util.Timer 类之上的，其使用了一个后台 Thread， 并且具有与
     * 标准线程相同的限制。随后，JDK 提供了 java.util.concurrent 包，它定义了 ScheduledExecutorService
     * 接口。如下是 java.util.concurrent.Executors 的相关工厂方法。
     *
     * （1）创建一个 ScheduledThreadExecutorService，用于调度命令在指定延迟之后运行或者周期性地执行。
     * 它使用 corePoolSize 参数来计算线程数：
     * 1）newScheduledThreadPool(int corePoolSize)
     * 2）newScheduledThreadPool(int corePoolSize,ThreadFactory threadFactory)
     *
     * （2）创建一个 ScheduledThreadExecutorService，用于调度命令在指定延迟之后运行或者周期性地执行。
     * 它使用一个线程来执行被调度的任务：
     * 1）newSingleThreadScheduledExecutor()
     * 2）newSingleThreadScheduledExecutor(ThreadFactory threadFactory)
     *
     * 虽然选择不是很多，但是这些预置的实现已经足以应对大多数的用例。
     *
     * PS：由 JDK 提供的这个接口的唯一具体实现是 java.util.concurrent.ScheduledThreadPoolExecutor。
     *
     * 以 ScheduleExamples 中的 schedule() 方法为例，展示了如何使用 ScheduledExecutorService 来在
     * 60 秒的延迟之后执行一个任务。
     *
     * 虽然 ScheduledExecutorService API 是直截了当的，但是在高负载下它将带来性能上的负担。下面将看到
     * Netty 是如何以更高的效率提供相同的功能的。
     *
     *
     *
     * 使用 EventLoop 调度任务
     *
     * ScheduledExecutorService 的实现具有局限性，例如，事实上作为线程池管理的一部分，将会有额外的线程
     * 创建。如果有大量任务被紧凑地调度，那么这将成为一个瓶颈。Netty 通过 Channel 的 EventLoop 实现任
     * 务调度解决了这一问题。
     *
     * 以 ScheduleExamples 中的 schedule() 方法为例，展示了使用 EventLoop 调度任务。
     *
     * 经过 60 秒之后，Runnable 实例将由分配给 Channel 的 EventLoop 执行。如果要调度任务以每隔 60 秒
     * 执行一次，请使用 scheduleAtFixedRate() 方法。
     *
     * 以 ScheduleExamples 中的 scheduleFixedViaEventLoop() 方法为例，展示了使用 EventLoop 调度周
     * 期性的任务。
     *
     * Netty的 EventLoop 扩展了 ScheduledExecutorService，所以它提供了使用 JDK 实现可用的所有方法，
     * 包括在前面的示例中使用到的 schedule() 和 scheduleAtFixedRate() 方法。所有操作的完整列表可以在
     * ScheduledExecutorService 的 Javadoc 中找到，如下：
     *
     * http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html
     *
     * 要想取消或者检查（被调度任务的）执行状态，可以使用每个异步操作所返回的 ScheduledFuture。
     *
     * 以 ScheduleExamples 中的 cancelingTaskUsingScheduledFuture() 方法为例，展示了一个简单的取
     * 消操作。
     *
     * 这些例子说明，可以利用 Netty 的任务调度功能来获得性能上的提升。反过来，这些也依赖于底层的线程模型。
     */
    public static void main(String[] args) {

    }

}
