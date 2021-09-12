package ru.anani.lesson8;



import ru.anani.lesson8.annotation.Cache;
import ru.anani.lesson8.annotation.CachedType;

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
                return cacheInvoke(method, args);
            }
            System.out.println("Without annotation @Cache: ");
            return method.invoke(delegate, args);
        }

        public Object cacheInvoke(Method method, Object[] args) throws Throwable {
            Cache cacheClass = method.getAnnotation(Cache.class);

            if (cacheClass.cacheType().equals(CachedType.FILE)) {
                if (!cacheClass.fileNamePrefix().equals("")) {
                    lruCache.createCacheFile(cacheClass.fileNamePrefix(), false);
                } else {
                    lruCache.createCacheFile(method.getName(), false);
                }
            }

            if (lruCache.contains(cacheClass, args)) {
                return findObjectIntoCache(cacheClass, args);
            } else {
                return invokeAndWriteToCache(cacheClass, method, args);
            }
        }

        public Object invokeAndWriteToCache(Cache cache, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            Object result = method.invoke(delegate, args);
            lruCache.set(cache, result, args);
            return result;
        }

        public Object findObjectIntoCache(Cache cache, Object[] args) {
            return lruCache.find(cache, args);
        }
    }
}
