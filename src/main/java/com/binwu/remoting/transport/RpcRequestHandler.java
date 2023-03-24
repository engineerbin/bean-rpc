package com.binwu.remoting.transport;


import com.binwu.remoting.service.ZKServiceProvider;
import com.binwu.utils.SingletonFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcRequestHandler {
    private ZKServiceProvider zkServiceProvider;

    public RpcRequestHandler() {
        zkServiceProvider = SingletonFactory.getInstance(ZKServiceProvider.class);
    }




}
