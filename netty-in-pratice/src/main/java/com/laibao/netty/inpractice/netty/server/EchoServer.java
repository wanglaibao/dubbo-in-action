package com.laibao.netty.inpractice.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private static final int SERVER_PORT = 8899;
    public static void main(String[] args) {

        try {
            doStart();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static void doStart() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();

        //(1) 创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //(2) 创建ServerBootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    //(3) 指定所使用的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    //(4) 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(SERVER_PORT))
                    //(5) 添加一个EchoServerHandler到于Channel的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel socketChannel) throws Exception {
                                    //EchoServerHandler 被标注为@Shareable,所以我们可以总是使用同样的实例
                                    //这里对于所有的客户端连接来说,都会使用同一个EchoServerHandler,因为其被标注为@Sharable.
                                    ChannelPipeline pipeline=socketChannel.pipeline();
                                    pipeline.addLast(serverHandler);
                                }
                    });
            //(6) 异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture future = serverBootstrap.bind().sync();

            System.out.println(EchoServer.class.getName() + " started and listening for connections on " + future.channel().localAddress());

            //(7) 获取 Channel 的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            //(8) 关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
