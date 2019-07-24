package com.laibao.hello.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.laibao.hello.api.model.User;
import com.laibao.hello.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloDubboConsumerApplicationTest {

    @Reference(
            version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    UserService userService;

    @Test
    public void testGetUserFromDubbo() {
        User user = userService.getUser();
        System.out.println(user);
    }
}
