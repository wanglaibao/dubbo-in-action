package com.laibao.simple.rpc.consumer;

import com.laibao.simple.rpc.netty.NettyClient;
import com.laibao.simple.rpc.pubinterface.HelloService;

public class ClientBootStrap {

    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) {

        NettyClient consumer = new NettyClient();
        // 创建一个代理对象
        HelloService service = (HelloService) consumer.getBean(HelloService.class, providerName);

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(service.hello("are you ok ?"));
        }
    }
}
