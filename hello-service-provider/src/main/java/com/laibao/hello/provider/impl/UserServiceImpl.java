package com.laibao.hello.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.laibao.hello.api.model.User;
import com.laibao.hello.api.service.UserService;
import org.springframework.stereotype.Component;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"

)
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return new User("byiwnd",28);
    }
}
