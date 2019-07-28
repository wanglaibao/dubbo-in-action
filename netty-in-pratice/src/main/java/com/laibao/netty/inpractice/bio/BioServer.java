package com.laibao.netty.inpractice.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private ServerSocket serverSocket;

    public BioServer(int port) {
        try {
            this.serverSocket = new ServerSocket(8000);
            System.out.println("服务器启动成功  端口为 ： port : " + port);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    public void start() {
        new Thread(() -> doStart()).start();
    }

    private void doStart() {
        while (true) {
            try {
                // (1) 阻塞方法获取新的连接
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            } catch (IOException e) {
            }

        }
    }
}
