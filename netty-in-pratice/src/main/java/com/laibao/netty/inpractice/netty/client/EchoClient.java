package com.laibao.netty.inpractice.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private static final String SERVER_HOST = "localhost";

    private static final int SERVER_PORT = 8899;

    public static void main(String[] args) {
            try {
                doStart();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void doStart() throws Exception {
        final EchoClientHandler echoClientHandler = new EchoClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //指定 EventLoopGroup 以处理客户端事件；需要适用于 NIO 的实现
            bootstrap.group(group)
                    //适用于 NIO 传输的Channel 类型
                    .channel(NioSocketChannel.class)
                    //设置服务器的InetSocketAddress
                    .remoteAddress(new InetSocketAddress(SERVER_HOST, SERVER_PORT))
                    //在创建Channel时，向 ChannelPipeline中添加一个 EchoClientHandler实例
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(echoClientHandler);
                        }
                    });
            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture future = bootstrap.connect().sync();
            //阻塞，直到Channel 关闭
            future.channel().closeFuture().sync();
        } finally {
            //关闭线程池并且释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
