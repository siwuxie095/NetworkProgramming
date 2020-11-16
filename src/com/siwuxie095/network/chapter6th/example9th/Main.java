package com.siwuxie095.network.chapter6th.example9th;

/**
 * @author Jiajing Li
 * @date 2020-11-16 22:01:28
 */
public class Main {

    /**
     * 修改 ChannelPipeline
     *
     * ChannelHandler 可以通过添加、删除或者替换其他的 ChannelHandler 来实时地修改 ChannelPipeline 的布局。
     * 它也可以将它自己从 ChannelPipeline 中移除。这是 ChannelHandler 最重要的能力之一，下面将仔细地来看看它
     * 是如何做到的。如下列出了相关的方法：
     *
     * （1）将一个 ChannelHandler 添加到 ChannelPipeline 中：
     * 1）addFirst
     * 2）addBefore
     * 3）addAfter
     * 4）addLast
     *
     * （2）将一个 ChannelHandler 从 ChannelPipeline 中移除：
     * 1）remove
     *
     * （3）将 ChannelPipeline 中的一个 ChannelHandler 替换为另一个 ChannelHandler：
     * 1）replace
     *
     * 以 ModifyChannelPipeline 为例，展示了这些方法的使用。重组 ChannelHandler 的这种能力使得可以用它来轻松
     * 地实现极其灵活的逻辑。
     *
     *
     * 关于 ChannelHandler 的执行和阻塞：
     *
     * 通常 ChannelPipeline 中的每一个 ChannelHandler 都是通过它的 EventLoop（I/O 线程）来处理传递给它的事
     * 件的。所以至关重要的是不要阻塞这个线程，因为这会对整体的 I/O 处理产生负面的影响。
     *
     * 但有时可能需要与那些使用阻塞 API 的遗留代码进行交互。对于这种情况，ChannelPipeline 有一些接受一个
     * EventExecutorGroup 的 add() 方法。如果一个事件被传递给一个自定义的 EventExecutorGroup，它将被包含在
     * 这个 EventExecutorGroup 中的某个 EventExecutor 所处理，从而被从该 Channel 本身的 EventLoop 中移除。
     * 对于这种用例，Netty 提供了一个叫 DefaultEventExecutorGroup 的默认实现。
     *
     *
     * 除了这些操作，还有别的通过类型或者名称来访问 ChannelHandler 的方法。这些方法如下：
     *
     * （1）通过类型或者名称返回 ChannelHandler：
     * 1）get
     *
     * （2）返回和 ChannelHandler 绑定的 ChannelHandlerContext：
     * 1）context
     *
     * （3）返回 ChannelPipeline 中所有 ChannelHandler 的名称：
     * 1）names
     */
    public static void main(String[] args) {

    }

}
