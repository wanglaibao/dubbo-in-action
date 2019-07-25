package com.laibao.simple.rpc.framework.test;

import com.laibao.simple.rpc.framework.SimpleRpcFramework;

/**
 * 引用服务
 * SimpleRpcConsumer
 */
public class SimpleRpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService service = SimpleRpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
