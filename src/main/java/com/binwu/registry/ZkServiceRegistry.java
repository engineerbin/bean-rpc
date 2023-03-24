package com.binwu.registry;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;

@Slf4j
public class ZkServiceRegistry {
    public void registerService(String serviceName, InetSocketAddress inetSocketAddress){
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + serviceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        try {
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
