package ru.anani.lesson8;



import ru.anani.lesson8.annotation.Cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CacheProxy {

    private InvocationHandlerImpl handler;
    private Object delegate;
    private final LRUCache lruCache;

    public CacheProxy(String cacheRoot, String cacheFileName) {

        this.lruCache = new LRUCache(cacheRoot, cacheFileName);
    }

    public <T> T cache(T t) throws InstantiationException, IllegalAccessException {
        handler = new InvocationHandlerImpl(t);
        delegate = t;
        return (T) Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                handler);
    }

    class InvocationHandlerImpl implements InvocationHandler {

        private final Object delegate;
//        private final LRUCache<Integer, Integer> lruCache;

        InvocationHandlerImpl(Object delegate) {
            this.delegate = delegate;
//            this.lruCache = new LRUCache<>(16);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.isAnnotationPresent(Cache.class)) {
                System.out.println("With annotation @Cache:");
                Cache cacheClass = method.getAnnotation(Cache.class);
                switch (cacheClass.cacheType()) {
                    case FILE:
                        if (cacheClass.identityBy().length == 0) {
                            System.out.println("Identity by all arguments: ");
                        }
                        else {
                            System.out.println("Identity by not all arguments: ");
                        }
                        System.out.println("Into file cache: ");

                        if (lruCache.containsInFileCache(args)) {
                            System.out.println("Contain true!");
                            return lruCache.findInFileCache(args);
                        } else {
                            System.out.println("Contain false!");
                            Object result = method.invoke(delegate, args);
                            lruCache.setInFileCache(result, args);
                            return result;
                        }
                    case IN_MEMORY:
                        System.out.println("Into memory cache: ");
                        if (cacheClass.identityBy().length == 0) {
                            System.out.println("Identity by all arguments: ");
                        }
                        else {
                            System.out.println("Identity by not all arguments: ");
                        }
                        if (lruCache.containsInMemoryCache(args)) {
                            System.out.println("Contain true!");
                            return lruCache.findInMemoryCache(args);
                        } else {
                            System.out.println("Contain false!");
                            Object result = method.invoke(delegate, args);
                            lruCache.setInMemoryCache(result, args);
                            return result;
                        }
                }
            }
            System.out.println("Without annotation @Cache: ");
            return method.invoke(delegate, args);
        }
    }
}
