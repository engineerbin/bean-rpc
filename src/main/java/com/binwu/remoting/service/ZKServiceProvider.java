package com.binwu.remoting.service;

import com.binwu.config.RpcServiceConfig;
import com.binwu.extension.ExtensionLoader;
import com.binwu.registry.ZkServiceRegistry;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ZKServiceProvider {
    private final Map<String, Object> serviceMap;
    private final Set<String> registeredService;
    private final ZkServiceRegistry zkServiceRegistry;
    public static final int PORT = 9998;

    public ZKServiceProvider() {
        serviceMap = Maps.newConcurrentMap();
        registeredService = ConcurrentHashMap.newKeySet();
        zkServiceRegistry = ExtensionLoader.getExtensionLoader(ZkServiceRegistry.class).getExtension("zk");
    }

    public void publishService(RpcServiceConfig config) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            addService(config);
            zkServiceRegistry.registerService(config.getServiceName(), new InetSocketAddress(host, PORT));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void addService(RpcServiceConfig config){
        String name = config.getRpcServiceName();
        registeredService.add(name);
        serviceMap.put(name, config.getService());
        log.info("Add service: {} and interfaces:{}", name, config.getService().getClass().getInterfaces());
    }

}
