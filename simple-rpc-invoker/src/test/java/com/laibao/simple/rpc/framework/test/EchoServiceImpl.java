package com.laibao.simple.rpc.framework.test;

public class EchoServiceImpl implements EchoService{
    @Override
    public String sayHello(String name) {
        return name == null ? "hello nobody" : "hello " + name;
    }
}
