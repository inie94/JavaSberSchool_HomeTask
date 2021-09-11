package ru.anani.lesson8;

import ru.anani.lesson8.entities.*;

public class Solution {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        CacheProxy cacheProxy = new CacheProxy(
                "javasberschool/Lesson8/src/main/resources",
                "cache"
        );

        Service service = cacheProxy.cache(new ServiceImpl());
        System.out.println(service.run(null, 0D, null).toString());
        System.out.println(service.work(null));
        System.out.println(service.work(null));

//        Service service = cacheProxy.cache(new ServiceImpl());
//        Loader loader = cacheProxy.cache(new LoaderImpl());

    }
}
