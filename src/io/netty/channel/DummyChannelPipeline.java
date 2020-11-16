package io.netty.channel;

/**
 * @author Jiajing Li
 * @date 2020-11-16 22:09:41
 */
@SuppressWarnings("all")
public class DummyChannelPipeline extends DefaultChannelPipeline {

    public static final ChannelPipeline DUMMY_INSTANCE = new DummyChannelPipeline(null);

    public DummyChannelPipeline(Channel channel) {
        super(channel);
    }

}
