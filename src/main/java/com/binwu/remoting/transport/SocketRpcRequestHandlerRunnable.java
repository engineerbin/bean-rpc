package com.binwu.remoting.transport;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.binwu.remoting.dto.RpcRequest;
import com.binwu.remoting.dto.RpcResponse;
import com.binwu.utils.SingletonFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class SocketRpcRequestHandlerRunnable implements Runnable {

    private Socket socket;
    private RpcRequestHandler rpcRequestHandler;

    public SocketRpcRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            String jsonString = JSON.toJSONString(objectInputStream.readObject());
            RpcRequest rpcRequest = JSON.parseObject(jsonString, RpcRequest.class);
            Object result = rpcRequestHandler.handle(rpcRequest);
            objectOutputStream.writeObject(RpcResponse.success(result, rpcRequest.getRequestId()));
            objectOutputStream.flush();
        } catch (Exception e) {
            log.error("occur exception:", e);
        }
    }
}
