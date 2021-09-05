package ru.anani.lesson1;

import java.util.Arrays;
import java.util.List;

public class BubbleSorting {
    public static void main(String[] args) {
//        List<Character> list = Arrays.asList('y', 'g', 's', 'j', 'w', 'e', 'u');
//        List<Integer> list = Arrays.asList(0, 7, 3, 9, 5, 2, 6, 1, 8, 4);
        List<String> list = Arrays.asList("F", "B", "J", "D", "H", "E", "A", "G", "I", "C");

        printList(list);

        bubbleSort(list);

        printList(list);
    }

    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.print(item);
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int j = 0; j < list.size() - 1; j++) {
                T temp = list.get(j);
                if (list.get(j + 1).compareTo(temp) < 0) {
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    isSorted = false;
                }
            }
        }
    }
}
