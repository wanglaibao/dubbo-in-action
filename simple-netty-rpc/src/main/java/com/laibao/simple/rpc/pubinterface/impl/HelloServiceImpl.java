package com.laibao.simple.rpc.pubinterface.impl;

import com.laibao.simple.rpc.pubinterface.HelloService;

/**
 * 实现类
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return name == null ? " -----> I am fine." : "I am fine."+name;
    }
}
