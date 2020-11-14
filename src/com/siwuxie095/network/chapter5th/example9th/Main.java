package com.siwuxie095.network.chapter5th.example9th;

/**
 * @author Jiajing Li
 * @date 2020-11-14 15:04:31
 */
public class Main {

    /**
     * 小结
     *
     * 这里专门探讨了 Netty 的基于 ByteBuf 的数据容器。首先解释了 ByteBuf 相对于 JDK 所提供的实现的优势。
     * 同时还强调了该 API 的其他可用变体，并且指出了它们各自最佳适用的特定用例。
     *
     * 这里讨论过的要点有：
     * （1）使用不同的读索引和写索引来控制数据访问；
     * （2）使用内存的不同方式 —— 基于字节数组和直接缓冲区；
     * （3）通过 CompositeByteBuf 生成多个 ByteBuf 的聚合视图；
     * （4）数据访问方法 —— 搜索、切片以及复制；
     * （5）读、写、获取和设置 API；
     * （6）ByteBufAllocator 池化和引用计数。
     */
    public static void main(String[] args) {

    }

}
