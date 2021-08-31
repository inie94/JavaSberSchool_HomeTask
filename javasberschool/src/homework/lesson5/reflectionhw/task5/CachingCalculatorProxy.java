package homework.lesson5.reflectionhw.task5;

import homework.lesson5.reflectionhw.task1.Calculator;
import homework.lesson5.reflectionhw.task1.CalculatorImpl;
import homework.lesson5.reflectionhw.task5.cache.LRUCache;

public class CachingCalculatorProxy implements Calculator {

    private final CalculatorImpl calculator;
    private final LRUCache<Integer, Integer> lruCache;

    public CachingCalculatorProxy() {
        this.calculator = new CalculatorImpl();
        this.lruCache = new LRUCache<>(16);
    }

    @Override
    public int calc(int number) {
        Integer value = (Integer) lruCache.find(number);
        if (value == null) {
            value = calculator.calc(number);
            lruCache.set(number, value);
        }
        return value;
    }
}
