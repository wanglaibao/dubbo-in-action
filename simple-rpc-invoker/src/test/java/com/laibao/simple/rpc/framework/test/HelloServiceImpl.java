package com.laibao.simple.rpc.framework.test;

/**
 * 实现服务
 * HelloServiceImpl
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello, " + name;
    }
}
