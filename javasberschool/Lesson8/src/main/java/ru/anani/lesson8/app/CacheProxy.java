package ru.anani.lesson8.app;

import ru.anani.lesson8.annotation.Cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CacheProxy {

    private final LRUCache lruCache;

    public CacheProxy(String cacheRoot) {
        this.lruCache = new LRUCache(cacheRoot);
    }

    public <T> T cache(T t) {
        return (T) Proxy.newProxyInstance(
                t.getClass().getClassLoader(),
                t.getClass().getInterfaces(),
                new InvocationHandlerImpl(t));
    }

    class InvocationHandlerImpl implements InvocationHandler {

        private final Object delegate;

        InvocationHandlerImpl(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.isAnnotationPresent(Cache.class)) {
                System.out.println("With annotation @Cache:");
                lruCache.setCacheParams(method.getAnnotation(Cache.class));
                lruCache.config(method.getName());
                return cacheInvoke(method, args);
            }
            System.out.println("Without annotation @Cache: ");
            return method.invoke(delegate, args);
        }

        public Object cacheInvoke(Method method, Object[] args) throws Throwable {
            if (lruCache.contains(args)) {
                System.out.println("Cache contains value true: ");
                return findObjectIntoCache(args);
            } else {
                System.out.println("Cache contains value false:");
                return invokeAndWriteToCache(method, args);
            }
        }
        public Object findObjectIntoCache( Object[] args) {
            return lruCache.find(args);
        }

        public Object invokeAndWriteToCache(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            Object result = method.invoke(delegate, args);
            lruCache.set(result, args);
            return result;
        }


    }
}
