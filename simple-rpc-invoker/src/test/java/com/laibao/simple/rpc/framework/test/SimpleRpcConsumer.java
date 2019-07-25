package com.laibao.simple.rpc.framework.test;

import com.laibao.simple.rpc.framework.SimpleRpcFramework;

/**
 * 引用服务
 * SimpleRpcConsumer
 */
public class SimpleRpcConsumer {
    //String, Class 都实现了Serializable接口，唯一可能出错的是参数，这里参数是String,所以不会出错。
    // output.writeObject就包含了序列化这个动作了.
    public static void main(String[] args) throws Exception {
        HelloService service = SimpleRpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
