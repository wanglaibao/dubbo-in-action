package com.laibao.simple.rpc.framework.test;

import com.laibao.simple.rpc.framework.SimpleRpcExporter;
import com.laibao.simple.rpc.framework.SimpleRpcImporter;

import java.net.InetSocketAddress;

/**
 * 简单RPC框架的测试
 * @see SimpleRpcExporter
 * @see SimpleRpcImporter
 */
public class SimpleRpcClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                SimpleRpcExporter.exporter("127.0.0.1", 8088);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        SimpleRpcImporter<EchoService> importer = new SimpleRpcImporter<>();
        EchoService echoService = importer.importer(EchoServiceImpl.class, new InetSocketAddress("127.0.0.1", 8088));
        System.out.println(echoService.sayHello("金戈"));
    }
}
