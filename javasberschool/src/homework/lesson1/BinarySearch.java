package homework.lesson1;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(0, 7, 3, 9, 5, 2, 6, 1, 8, 4);
        List<String> list = Arrays.asList("F", "B", "J", "D", "H", "E", "A", "G", "I", "C");

        list.stream().forEach(item -> System.out.printf(item.toString()));
        System.out.println();

        list.sort((o1, o2) -> o1.compareTo(o2));

        list.stream().forEach(item -> System.out.printf(item.toString()));
        System.out.println();

        int index = binarySearch(list, "C", 0, list.size() - 1);

        System.out.println(index);
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> list, T searchingItem, int left, int right) {
        int index = (right + left)/2;
        int compareResult = list.get(index).compareTo(searchingItem);
        if (compareResult > 0) {
            return binarySearch(list, searchingItem, left, index);
        } else if (compareResult < 0) {
            return binarySearch(list, searchingItem, index, right);
        } else {
            return index;
        }
    }

}
