package homework.lesson1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 7, 3, 9, 5, 2, 6, 1, 8, 4);
//        List<String> list = Arrays.asList("F", "B", "J", "D", "H", "E", "A", "G", "I", "C");

        list.forEach(item -> System.out.print(item.toString()));
        System.out.println();

        list.sort(Comparator.naturalOrder());

        list.forEach(item -> System.out.print(item.toString()));
        System.out.println();

        int index = binarySearch(list, 6, 0, list.size() - 1);

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
