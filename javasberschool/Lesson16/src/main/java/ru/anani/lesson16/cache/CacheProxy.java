package ru.anani.lesson16.cache;

import ru.anani.lesson16.source.Source;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CacheProxy {

    private Source source;

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
            if(method.isAnnotationPresent(Cacheable.class)) {
                System.out.println("With annotation @Cacheable:");
                return cacheInvoke(method, args);
            }
            System.out.println("Without annotation @Cacheable: ");
            source.closeConnection();
            return method.invoke(delegate, args);
        }

        public Object cacheInvoke(Method method, Object[] args) throws Throwable {
            Cacheable annotation = method.getAnnotation(Cacheable.class);
            source = annotation.value().newInstance();
            if(source.contains((Integer) args[0])) {
                System.out.println("Cache contains value true:");
                List<Integer> result = source.getAllValuesUpToInclusive((Integer) args[0]);
                source.closeConnection();
                return result;
            } else {
                System.out.println("Cache contains value false:");
                return invokeAndWriteToCache(args);
            }
        }

        public Object invokeAndWriteToCache(Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
            List<Integer> previousIntegers = source.getAllValues();
            Method method1 = delegate.getClass().getMethod("fibonacci", Integer.class, List.class);
            List<Integer> result = (List<Integer>) method1.invoke(delegate, args[0], previousIntegers);
            List<Integer> temp = new ArrayList<>(result);

            Iterator<Integer> iterator = temp.iterator();
            int i = 0;
            while (i < previousIntegers.size()) {
                if(iterator.next().equals(previousIntegers.get(i))) {
                    iterator.remove();
                }
                i++;
            }

            source.saveAll(temp);
            source.closeConnection();
            return result;
        }

    }
}
