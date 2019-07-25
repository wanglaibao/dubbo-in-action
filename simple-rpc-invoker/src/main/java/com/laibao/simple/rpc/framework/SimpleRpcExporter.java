package com.laibao.simple.rpc.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * RPC服务端 服务发布者
 *
 *
 * 服务发布者的主要职责如下:
 *      1: 作为服务端,监听客户端的Socket链接,接收到客户端的链接之后封装成Task交给线程池执行
 *      2: 将客户端发送的字节流反序列化成对象,反射调用服务的实现者,获取执行结果
 *      3: 将结果反序列化,通过Socket发送给客户端
 *      4: 远程服务调用结束后,释放资源
 */
public class SimpleRpcExporter {

    private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     *
     * @param hostName
     * @param port
     * @throws Exception
     */
    public static void exporter(String hostName, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName, port));
        try {
            while (true) {
                executor.execute(new ExporterTasks(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }
    }

    private static class ExporterTasks implements Runnable {
        Socket client = null;

        public ExporterTasks(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream out = null;
            try {
                input = new ObjectInputStream(client.getInputStream());
                String interfaceName = input.readUTF();
                Class<?> service = Class.forName(interfaceName);
                String methodName = input.readUTF();
                Class<?>[] paramsTypes = (Class<?>[]) input.readObject();
                Object[] args = (Object[]) input.readObject();
                Method method = service.getMethod(methodName, paramsTypes);
                Object result = method.invoke(service.newInstance(), args);
                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null)  {
                        out.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                    if (client != null) {
                        client.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
