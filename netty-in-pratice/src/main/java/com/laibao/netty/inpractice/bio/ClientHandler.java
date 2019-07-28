package com.laibao.netty.inpractice.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {

    private static final int MAX_DATA_LEN = 1024;

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("新的客户端接入进来了啊....");
        //每一个新的连接都创建一个线程，负责读取数据
        new Thread(() -> doStart()).start();
    }

    private void doStart() {
        try {
            byte[] data = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int len;
                //按字节流方式读取数据
                while ((len = inputStream.read(data)) != -1) {
                    System.out.println(new String(data, 0, len));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
