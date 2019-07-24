package com.laibao.hello.provider;

import com.laibao.hello.provider.annotation.Say;
import org.springframework.stereotype.Component;

@Component
public class AopRunner {

    @Say
    public Object testAop(){
        return null;
    }
}
