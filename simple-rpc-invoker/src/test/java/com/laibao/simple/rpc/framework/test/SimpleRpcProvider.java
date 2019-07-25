package com.laibao.simple.rpc.framework.test;

import com.laibao.simple.rpc.framework.SimpleRpcFramework;

/**
 * 暴露服务
 * SimpleRpcProvider
 */
public class SimpleRpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        SimpleRpcFramework.export(service, 1234);
    }
}
