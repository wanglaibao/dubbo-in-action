package com.laibao.simple.rpc.provider;

import com.laibao.simple.rpc.netty.NettyServer;

public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("localhost", 8088);
    }
}
