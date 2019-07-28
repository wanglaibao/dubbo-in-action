package com.laibao.hello.consumer;

import com.laibao.hello.api.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServiceConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:META-INFO/spring/dubbo-demo-consumer.xml"});
        context.start();
        // 获取远程服务代理对象
        DemoService demoService = (DemoService)context.getBean("demoService");

        // 通过代理对象来执行远程方法
        String hello = demoService.sayHello("金戈,你在哪里啊,你妈妈喊里你回家吃饭啦!!!!!!!");

        // 显示调用结果
        System.out.println( hello );
    }
}
