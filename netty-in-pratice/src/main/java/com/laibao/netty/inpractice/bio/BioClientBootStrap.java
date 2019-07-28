package com.laibao.netty.inpractice.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * BIO编程
 * 场景模拟：客户端每隔5秒发送一个带有时间戳的"hello world"给服务端,服务端收到之后打印.
 */
public class BioClientBootStrap {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int SLEEP_TIME = 5000;

    public static void main(String[] args) {
        try {
            final Socket socket = new Socket(HOST, PORT);
            new Thread(() -> {
                    while (true) {
                        try {
                            socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                            socket.getOutputStream().flush();
                            sleep();
                        } catch (Exception e) {
                        }
                    }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
