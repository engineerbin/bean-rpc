package com.binwu.extension;

import com.binwu.annotation.SPI;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ExtensionLoader<T> {
    private static final String SERVICE_DIRECTORY = "META-INF/extensions/";
    private final Class<?> type;
    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();

    private ExtensionLoader(Class<?> type) {
        this.type = type;
    }

    public static <C> ExtensionLoader<C> getExtensionLoader(Class<C> type){
        if (type == null) {
            throw new IllegalArgumentException("Extension type should not be null.");
        }
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type must be an interface.");
        }
        if (type.getAnnotation(SPI.class) == null) {
            throw new IllegalArgumentException("Extension type must be annotated by @SPI");
        }
        return new ExtensionLoader<C>(type);
    }

    public T getExtension(String name){
        Holder<Object> holder = new Holder<>();
        Object instance = holder.get();
        return (T) instance;
    }



}
