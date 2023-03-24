package com.binwu.utils;


import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ThreadPoolFactory {
    public static final Map<String, ExecutorService> THREAD_POOLS = Maps.newConcurrentMap();

    public ThreadPoolFactory() {
    }

    public static ExecutorService getThreadPoolIfAbsent(String name) {
        ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
        return getThreadPoolIfAbsent(threadPoolConfig, name, false);
    }

    private static ExecutorService getThreadPoolIfAbsent(ThreadPoolConfig config, String name, boolean daemon) {
        return THREAD_POOLS.computeIfAbsent(name, k -> createThreadPool(config, name, daemon));
    }

    private static ExecutorService createThreadPool(ThreadPoolConfig config, String name, boolean daemon) {
        ThreadFactory threadFactory = createThreadFactory(name, daemon);
        return new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaximumPoolSize(), config.getKeepAliveTime(),
                config.getUnit(), config.getWorkQueue(), threadFactory);
    }

    private static ThreadFactory createThreadFactory(String name, boolean daemon) {
        return new ThreadFactoryBuilder().setNameFormat(name + "-%d").setDaemon(daemon).build();
    }
}
