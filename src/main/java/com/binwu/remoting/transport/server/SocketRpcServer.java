package com.binwu.remoting.transport.server;


import com.binwu.utils.ThreadPoolFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

@Slf4j
public class SocketRpcServer {

    private final ExecutorService threadPool;

    public SocketRpcServer() {
        threadPool = ThreadPoolFactory.getThreadPoolIfAbsent("socket-server-rpc-pool");
    }


    public void start(){

    }



}
