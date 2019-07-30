package com.laibao.netty.inpractice.test.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TestSocketClient {

    public static void main(String[] args) throws Exception{
        EventLoopGroup clientGroup = new NioEventLoopGroup(); //用来处理任务的

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)  //通过反射的方式来进行创建的
                    .handler(new TestSocketClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            clientGroup.shutdownGracefully().sync();
        }
    }
}
