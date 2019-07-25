package com.laibao.simple.rpc.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * RPC客户端 本地服务代理【接口代理】
 *
 * 本地服务的代理的功能如下:
 *      1:将本地接口调用转换成JDK的动态代理,在动态代理中实现接口的调用
 *      2:创建Socket客户端,根据指定地址调用远程服务的提供者
 *      3:将远程接口调用所需要的接口类,方法名,参数列表等编码后发送给服务提供者
 *      4:同步阻塞等待服务端返回应答,获取执行结果
 *
 */
public class SimpleRpcImporter<T> {

    public T importer(final Class<?> serviceClass, final InetSocketAddress address){
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[]{serviceClass.getInterfaces()[0]},
                                    (proxy, method, args) -> {
                        Socket socket = null;
                        ObjectOutputStream outputStream = null;
                        ObjectInputStream inputStream = null;
                        try {
                            socket = new Socket();
                            socket.connect(address);
                            outputStream = new ObjectOutputStream(socket.getOutputStream());
                            outputStream.writeUTF(serviceClass.getName());
                            outputStream.writeUTF(method.getName());
                            outputStream.writeObject(method.getParameterTypes());
                            outputStream.writeObject(args);
                            inputStream = new ObjectInputStream(socket.getInputStream());
                            return inputStream.readObject();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if(outputStream != null) {
                                    outputStream.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (socket != null) {
                                    socket.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    });
    }
}
