package com.laibao.hello.provider.impl;

import com.laibao.hello.api.service.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"

)
@Component
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
