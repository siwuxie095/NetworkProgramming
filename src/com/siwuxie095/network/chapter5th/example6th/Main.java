package com.siwuxie095.network.chapter5th.example6th;

/**
 * @author Jiajing Li
 * @date 2020-11-13 07:45:01
 */
public class Main {

    /**
     * ByteBufHolder 接口
     *
     * 经常发现，除了实际的数据负载之外，还需要存储各种属性值。HTTP 响应便是一个很好的例子，除了表示为字节的内容，
     * 还包括状态码、cookie 等。
     *
     * 为了处理这种常见的用例，Netty 提供了 ByteBufHolder。ByteBufHolder 也为 Netty 的高级特性提供了支持，
     * 如缓冲区池化，其中可以从池中借用 ByteBuf，并且在需要时自动释放。
     *
     * ByteBufHolder 只有几种用于访问底层数据和引用计数的方法。如下所示（这里不包括它继承自 ReferenceCounted
     * 接口的那些方法）：
     * （1）content()：返回由这个 ByteBufHolder 所持有的 ByteBuf；
     * （2）copy()：返回这个 ByteBufHolder 的一个深拷贝，包括一个其所包含的 ByteBuf 的非共享拷贝；
     * （3）duplicate()：返回这个 ByteBufHolder 的一个浅拷贝，包括一个其所包含的 ByteBuf 的共享拷贝。
     *
     * 如果想要实现一个将其有效负载存储在 ByteBuf 中的消息对象，那么 ByteBufHolder 将是个不错的选择。
     */
    public static void main(String[] args) {

    }

}
