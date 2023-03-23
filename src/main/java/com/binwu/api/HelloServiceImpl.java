package com.binwu.api;


import com.binwu.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RpcService(group = "test", version = "v1")
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(Message message) {
        log.info("HelloServiceImpl receive:{}", message.getContent());
        String result = "Hello description is " + message.getDescription();
        log.info("HelloServiceImpl return:{}",result);
        return result;
    }
}
