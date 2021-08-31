package homework.lesson5.reflectionhw.task6;

import homework.lesson5.reflectionhw.task1.Calculator;
import homework.lesson5.reflectionhw.task5.cache.LRUCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {

    private final Calculator delegate;
    private final LRUCache<Integer, Integer> lruCache;

    public PerformanceProxy(Calculator delegate){
        this.delegate = delegate;
        this.lruCache = new LRUCache<>(16);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Metric.class)) return metricInvoke(method,args);
        return invoke(method,args);
    }

    private Object metricInvoke(Method method, Object[] args) throws Throwable {
        try {
            long start = System.nanoTime();
            Integer result = (Integer) invoke(method,args);
            long end = System.nanoTime();
            System.out.println("Method running time: " + (end - start) + " nanoseconds.");
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible",e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }



    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            Integer result = (Integer) lruCache.find((Integer) args[0]);
            if (result == null) {
                result = (Integer) method.invoke(delegate, args);
                lruCache.set((Integer) args[0], result);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible",e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }



    }
}