package com.siwuxie095.network.chapter11th.example3rd;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 添加 HTTP 支持
 *
 * @author Jiajing Li
 * @date 2020-11-28 18:27:19
 */
@SuppressWarnings("all")
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private final boolean isClient;

    public HttpPipelineInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (isClient) {
            // 如果是客户端，则添加 HttpResponseDecoder 以处理来自服务器的响应
            pipeline.addLast("decoder", new HttpResponseDecoder());
            // 如果是客户端，则添加 HttpRequestEncoder 以向服务器发送请求
            pipeline.addLast("encoder", new HttpRequestEncoder());
        } else {
            // 如果是服务器，则添加 HttpRequestDecoder 以接收来自客户端的请求
            pipeline.addLast("decoder", new HttpRequestDecoder());
            // 如果是服务器，则添加 HttpResponseEncoder 以向客户端发送响应
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
    }
}

