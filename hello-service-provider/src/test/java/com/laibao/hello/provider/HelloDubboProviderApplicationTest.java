package com.laibao.hello.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloDubboProviderApplicationTest {


    @Autowired
    AopRunner runner;

    @Test
    public void contextLoads() {
        System.err.println("启动了");
    }


    @Test
    public void aop(){
        Object o = runner.testAop();
        System.out.println(o);
    }
}
