package ru.anani.lesson8.app;

import ru.anani.lesson8.entities.Service;
import ru.anani.lesson8.entities.ServiceImpl;

import java.util.Date;


public class Solution {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        CacheProxy cacheProxy = new CacheProxy("javasberschool/Lesson8/src/main/resources");

        Service service = cacheProxy.cache(new ServiceImpl());
        System.out.println("Result \"run\" is: " + service.run(null, 0D, null).toString());
        System.out.println("Result \"run\" is: " + service.run(null, 0D, new Date()).toString());
        System.out.println("Result \"run\" is: " + service.run(null, 4D, null).toString());
        System.out.println("Result \"run\" is: " + service.run(null, 4D, new Date()).toString());
        System.out.println("Result \"run\" is: " + service.run(null, 0D, null).toString());
        System.out.println("Result \"run\" is: " + service.run(null, 4D, new Date()).toString());
//        System.out.println("Result \"work\" is: " + service.work(null));
//        System.out.println("Result \"work\" is: " + service.work(null));

//        Service service = cacheProxy.cache(new ServiceImpl());
//        Loader loader = cacheProxy.cache(new LoaderImpl());

    }
}
