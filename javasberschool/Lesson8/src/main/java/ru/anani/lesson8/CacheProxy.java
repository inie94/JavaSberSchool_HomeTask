package ru.anani.lesson8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheProxy {

    private final InvocationHandlerImpl handler;
    public CacheProxy() {
        this.handler = new InvocationHandlerImpl();
    }

    public <T> T cache(T t) throws InstantiationException, IllegalAccessException {
        return t;
    }

    class InvocationHandlerImpl implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
