package homework.lesson5.reflectionhw.task1;

import homework.lesson5.reflectionhw.task6.Metric;

public interface Calculator {
    /**
     * Расчет факториала числа.
     * @param number
     */
    @Metric
    int calc (int number);
}
