package com.laibao.hello.provider.impl;

import com.laibao.hello.api.service.EchoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());

        System.out.println("[" + now + "] Hello " + message
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());

        return message;
    }
}
