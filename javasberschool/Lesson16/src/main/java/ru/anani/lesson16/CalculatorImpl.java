package ru.anani.lesson16;

import java.util.*;

public class CalculatorImpl implements Calculator{

    @Override
    public List<Integer> fibonacci(int n) {
        if (n <= 0) throw new RuntimeException("Wrong number of Fibonacci numbers. N must be >= 1");
        if (n == 1) return Collections.singletonList(0);
        List<Integer> fibonacciNumbers = new ArrayList<>(Arrays.asList(0, 1));
        if (n == 2) return fibonacciNumbers;
        for (int i = 2; i < n; i++) {
            int a = fibonacciNumbers.get(fibonacciNumbers.size() - 1);
            a = a + fibonacciNumbers.get(fibonacciNumbers.size() - 2);
            fibonacciNumbers.add(a);
        }
        return fibonacciNumbers;
    }

    public List<Integer> fibonacci(Integer n, List<Integer> previousIntegers) {
        if (n <= 0) throw new RuntimeException("Wrong number of Fibonacci numbers. N must be >= 1");
        if (n == 1) return Collections.singletonList(0);
        if (n == 2) return new ArrayList<>(Arrays.asList(0, 1));
        List<Integer> result = new ArrayList<>(previousIntegers);
        for (int i = previousIntegers.size(); i < n; i++) {
            int a = result.get(result.size() - 1);
            a = a + result.get(result.size() - 2);
            result.add(a);
        }
        return result;
    }
}
