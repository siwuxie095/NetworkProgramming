package com.siwuxie095.network.chapter10th.example5th;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 通过该解码器和编码器实现参数化 CombinedByteCharCodec
 *
 * @author Jiajing Li
 * @date 2020-11-25 22:24:37
 */
@SuppressWarnings("all")
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        // 将委托实例传递给父类
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }

}
