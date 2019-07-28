package com.laibao.hello.provider;
import com.laibao.hello.api.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServiceProvider {

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:META-INFO/spring/dubbo-demo-provider.xml"});
            context.start();
            DemoService demoService = context.getBean(DemoService.class);
            System.out.println(demoService.sayHello("金戈,你这个大SB"));
            System.out.println("...................................");
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
