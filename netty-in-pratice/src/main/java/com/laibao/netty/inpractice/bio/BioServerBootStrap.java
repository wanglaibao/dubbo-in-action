package com.laibao.netty.inpractice.bio;

public class BioServerBootStrap {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        BioServer bioServer = new BioServer(PORT);
        bioServer.start();
    }
}
