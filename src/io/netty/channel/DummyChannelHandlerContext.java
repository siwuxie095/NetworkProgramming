package io.netty.channel;

import io.netty.util.concurrent.EventExecutor;

/**
 * @author Jiajing Li
 * @date 2020-11-13 08:10:50
 */
@SuppressWarnings("all")
public class DummyChannelHandlerContext extends AbstractChannelHandlerContext {

    public static ChannelHandlerContext DUMMY_INSTANCE = new DummyChannelHandlerContext(
            null,
            null,
            "dummyChannelHandlerContext"
    );

    public DummyChannelHandlerContext(DefaultChannelPipeline pipeline,
                                      EventExecutor executor,
                                      String name) {
        super(pipeline, executor, name, ChannelDuplexHandler.class);
    }

    @Override
    public ChannelHandler handler() {
        return null;
    }

}

