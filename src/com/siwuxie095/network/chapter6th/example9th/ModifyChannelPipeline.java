package com.siwuxie095.network.chapter6th.example9th;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelPipeline;

import static io.netty.channel.DummyChannelPipeline.DUMMY_INSTANCE;

/**
 * 修改 ChannelPipeline
 *
 * @author Jiajing Li
 * @date 2020-11-16 22:07:50
 */
@SuppressWarnings("all")
public class ModifyChannelPipeline {

    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DUMMY_INSTANCE;

    /**
     * 修改 ChannelPipeline
     */
    public static void modifyPipeline() {
        // get reference to pipeline;
        ChannelPipeline pipeline = CHANNEL_PIPELINE_FROM_SOMEWHERE;
        // 创建一个 FirstHandler 的实例
        FirstHandler firstHandler = new FirstHandler();
        // 将该实例作为 "handler1" 添加到 ChannelPipeline 中
        pipeline.addLast("handler1", firstHandler);
        // 将一个 SecondHandler 的实例作为 "handler2" 添加到 ChannelPipeline 的第一个槽中。这意味着它将被放置在已有的 "handler1" 之前
        pipeline.addFirst("handler2", new SecondHandler());
        // 将一个 ThirdHandler 的实例作为 "handler3" 添加到 ChannelPipeline 的最后一个槽中
        pipeline.addLast("handler3", new ThirdHandler());
        // ...
        // 通过名称移除 "handler3"
        pipeline.remove("handler3");
        // 通过引用移除 FirstHandler（它是唯一的，所以不需要它的名称）
        pipeline.remove(firstHandler);
        // 将 SecondHandler("handler2") 替换为 FourthHandler:"handler4"
        pipeline.replace("handler2", "handler4", new FourthHandler());
    }

    private static final class FirstHandler extends ChannelHandlerAdapter {

    }

    private static final class SecondHandler extends ChannelHandlerAdapter {

    }

    private static final class ThirdHandler extends ChannelHandlerAdapter {

    }

    private static final class FourthHandler extends ChannelHandlerAdapter {

    }

}
