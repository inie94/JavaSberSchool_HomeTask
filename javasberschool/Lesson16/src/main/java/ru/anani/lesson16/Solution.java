package ru.anani.lesson16;

import ru.anani.lesson16.cache.CacheProxy;

public class Solution {
    public static void main(String[] args) {

        CacheProxy cacheProxy = new CacheProxy();
        Calculator calculator = cacheProxy.cache(new CalculatorImpl());
        System.out.println(1 + " " + calculator.fibonacci(1));
        System.out.println(2 + " " + calculator.fibonacci(2));
        System.out.println(3 + " " + calculator.fibonacci(3));
        System.out.println(4 + " " + calculator.fibonacci(4));
//        System.out.println(5 + " " + calculator.fibonacci(5));
//        System.out.println(6 + " " + calculator.fibonacci(6));
//        System.out.println(7 + " " + calculator.fibonacci(7));
        System.out.println(8 + " " + calculator.fibonacci(8));
    }
}
