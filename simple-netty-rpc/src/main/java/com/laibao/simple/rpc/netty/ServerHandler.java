package com.laibao.simple.rpc.netty;

import com.laibao.simple.rpc.consumer.ClientBootStrap;
import com.laibao.simple.rpc.pubinterface.impl.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * 用于处理请求数据
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果符合约定，则调用本地方法，返回数据
        if (msg.toString().startsWith(ClientBootStrap.providerName)) {
            String result = new HelloServiceImpl()
                    .hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }
}
