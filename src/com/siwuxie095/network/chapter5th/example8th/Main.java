package com.siwuxie095.network.chapter5th.example8th;

/**
 * @author Jiajing Li
 * @date 2020-11-14 14:54:09
 */
public class Main {

    /**
     * 引用计数
     *
     * 引用计数是一种通过在某个对象所持有的资源不再被其他对象引用时释放该对象所持有的资源来优化内存使用和性能的技术。
     * Netty 在第 4 版中为 ByteBuf 和 ByteBufHolder 引入了引用计数技术，它们都实现了 ReferenceCounted 接口。
     *
     * 引用计数背后的想法并不是特别的复杂。它主要涉及跟踪到某个特定对象的活动引用的数量。一个 ReferenceCounted
     * 实现的实例将通常以活动的引用计数为 1 作为开始。只要引用计数大于 0，就能保证对象不会被释放。当活动引用的数量
     * 减少到 0 时，该实例就会被释放。注意，虽然释放的确切语义可能是特定于实现的，但是至少已经释放的对象应该不可再
     * 用了。
     *
     * 引用计数对于池化实现（如 PooledByteBufAllocator）来说是至关重要的，它降低了内存分配的开销。
     *
     * 以 ReferenceCountedExamples 中的 referenceCounting() 和 releaseReferenceCountedObject() 方法
     * 为例，展示了相关的用法。
     *
     * 试图访问一个已经被释放的引用计数的对象，将会导致一个 IllegalReferenceCountException。
     *
     * 注意，一个特定的（ReferenceCounted 的实现）类，可以用它自己的独特方式来定义它的引用计数规则。例如，可以
     * 设想一个类，其 release() 方法的实现总是将引用计数设为零，而不用关心它的当前值，从而一次性地使所有的活动
     * 引用都失效。
     *
     * 谁负责释放：一般来说，是由最后访问（引用计数）对象的那一方来负责将它释放。
     */
    public static void main(String[] args) {

    }

}
