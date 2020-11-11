package com.siwuxie095.network.chapter5th.example2nd;

/**
 * @author Jiajing Li
 * @date 2020-11-11 21:05:57
 */
public class Main {

    /**
     * ByteBuf 的 API
     *
     * Netty 的数据处理 API 通过两个组件暴露，如下：
     * （1）ByteBuf 抽象类；
     * （2）ByteBufHolder 接口。
     *
     * 下面是一些 ByteBuf API 的优点：
     * （1）它可以被用户自定义的缓冲区类型扩展；
     * （2）通过内置的复合缓冲区类型实现了透明的零拷贝；
     * （3）容量可以按需增长（类似于 JDK 的 StringBuilder）；
     * （4）在读和写这两种模式之间切换不需要调用 ByteBuffer 的 flip() 方法（即 不需要像 ByteBuffer 一样调用 flip() 方法）；
     * （5）读和写使用了不同的索引；
     * （6）支持方法的链式调用；
     * （7）支持引用计数；
     * （8）支持池化。
     *
     * 其他类可用于管理 ByteBuf 实例的分配，以及执行各种针对于数据容器本身和它所持有的数据的操作。
     */
    public static void main(String[] args) {

    }

}
