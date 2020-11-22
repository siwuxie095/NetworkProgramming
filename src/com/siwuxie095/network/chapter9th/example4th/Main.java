package com.siwuxie095.network.chapter9th.example4th;

/**
 * @author Jiajing Li
 * @date 2020-11-22 22:12:37
 */
public class Main {

    /**
     * 测试异常处理
     *
     * 应用程序通常需要执行比转换数据更加复杂的任务。例如，你可能需要处理格式不正确的输入或者过量的数据。
     *
     * 以 FrameChunkDecoder 为例，如果所读取的字节数超出了某个特定的限制，这里将会抛出一个
     * TooLongFrameException。这是一种经常用来防范资源被耗尽的方法。
     *
     * 在 FrameChunkDecoder 中，最大的帧大小可以进行设置。如果一个帧的大小超出了该限制，那么程序将会
     * 丢弃它的字节，并抛出一个 TooLongFrameException。位于 ChannelPipeline 中的其他
     * ChannelHandler 可以选择在 exceptionCaught() 方法中处理该异常或者忽略它。
     *
     * 以 FrameChunkDecoderTest 为例，展示了使用 EmbeddedChannel 来测试这段代码。
     *
     * 测试代码中有一段比较有趣的地方，即 对 TooLongFrameException 的处理。这里使用的 try/catch 块
     * 中的代码是 EmbeddedChannel 的一个特殊功能。如果其中一个 write* 方法产生了一个受检查的
     * Exception，那么它将会被包装在一个 RuntimeException 中并抛出（需要注意的是，如果该类实现了
     * exceptionCaught() 方法并处理了该异常，那么它将不会被 catch 块所捕获）。这使得可以容易地测试出
     * 一个 Exception 是否在处理数据的过程中已经被处理了。
     *
     * 这里介绍的测试方法可以用于任何能抛出 Exception 的 ChannelHandler 实现。
     */
    public static void main(String[] args) {

    }

}
