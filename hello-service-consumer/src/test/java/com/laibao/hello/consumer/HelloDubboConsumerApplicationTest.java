package com.laibao.hello.consumer;

import com.laibao.hello.api.model.User;
import com.laibao.hello.api.service.DemoService;
import com.laibao.hello.api.service.EchoService;
import com.laibao.hello.api.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
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


    @Reference(
            version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    DemoService demoService;

    @Reference(
            version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    EchoService echoService;

    @Test
    public void testGetUserFromDubbo() {
        User user = userService.getUser();
        System.out.println(user);
    }

    @Test
    public void testDemoServiceFromDubbo() {
        String message = demoService.sayHello("金戈");
        System.out.println(message);
    }


    @Test
    public void testEchoServiceDubbo() {
        System.out.println(echoService.echo("金戈"));
    }
}
