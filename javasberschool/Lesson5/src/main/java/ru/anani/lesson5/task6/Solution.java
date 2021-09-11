package ru.anani.lesson5.task6;

import ru.anani.lesson5.task1.Calculator;
import ru.anani.lesson5.task1.CalculatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;

public class Solution {
    public static void main(String[] args) {

        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new PerformanceProxy(delegate));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String request;
            System.out.println("For close this app write \"exit\".");
            System.out.print("Write value for calculated: ");
            while (true) {
                if (reader.ready()) {
                    while (!(request = reader.readLine()).equals("exit")) {
                        System.out.println("Answer is: " + calculator.calc(Integer.parseInt(request)));

                        System.out.println("For close this app write \"exit\".");
                        System.out.print("Write value for calculated: ");
                    }
                    System.out.println("Goodbye!");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
