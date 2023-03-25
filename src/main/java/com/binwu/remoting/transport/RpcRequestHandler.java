package com.binwu.remoting.transport;


import com.binwu.remoting.dto.RpcRequest;
import com.binwu.remoting.service.ZKServiceProvider;
import com.binwu.utils.SingletonFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class RpcRequestHandler {
    private ZKServiceProvider zkServiceProvider;

    public RpcRequestHandler() {
        zkServiceProvider = SingletonFactory.getInstance(ZKServiceProvider.class);
    }

    public Object handle(RpcRequest request) {
        Object service = zkServiceProvider.getService(request.getRpcServiceName());
        return invokeTargetMethod(request, service);
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result = null;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
        }catch (Exception e){

        }
        return result;
    }


}
