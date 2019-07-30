package com.laibao.netty.inpractice.netty;

import io.netty.channel.AbstractServerChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRegistered(ChannelHandlerContext ctx)  {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        System.out.println("channelActive");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("handlerAdded");
    }
}
