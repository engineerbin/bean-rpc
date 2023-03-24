package com.binwu.contract;

import com.binwu.api.HelloService;
import com.binwu.api.HelloServiceImpl;
import com.binwu.remoting.transport.SocketRpcServer;

public class SocketServerMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();


    }
}
