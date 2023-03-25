package com.binwu.remoting.transport;


import com.binwu.config.CustomShutdownHook;
import com.binwu.config.RpcServiceConfig;
import com.binwu.remoting.service.ZKServiceProvider;
import com.binwu.utils.SingletonFactory;
import com.binwu.utils.ThreadPoolFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static com.binwu.remoting.service.ZKServiceProvider.PORT;

@Slf4j
public class SocketRpcServer {
    private final ExecutorService threadPool;
    private final ZKServiceProvider zkServiceProvider;

    public SocketRpcServer() {
        threadPool = ThreadPoolFactory.getThreadPoolIfAbsent("socket-server-rpc-pool");
        zkServiceProvider = SingletonFactory.getInstance(ZKServiceProvider.class);
    }

    public void registerService(RpcServiceConfig config) {
        zkServiceProvider.publishService(config);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            String host = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(host, PORT));
            CustomShutdownHook.getCustomShutdownHook().clearAll();
            Socket socket = null;
            while ((socket = serverSocket.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                threadPool.execute(new SocketRpcRequestHandlerRunnable(socket));
            }
            threadPool.shutdown();
        } catch (Exception e) {
            log.error("occur exception:", e);
        }

    }


}
