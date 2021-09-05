package ru.anani.lesson3.pecsrules;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Integer> dest = CollectionUtils.newArrayList();

        List<Integer> src = CollectionUtils.newArrayList();

        src.addAll(Arrays.asList(8, 1, 3, 5, 6, 4));
        src.forEach(System.out::print);
        System.out.println();

        CollectionUtils.addAll(src, dest);

        dest.forEach(System.out::print);
        System.out.println();

        System.out.println(dest.indexOf(3));

        CollectionUtils.limit(dest, 4).forEach(System.out::print);
        System.out.println();

        dest.add(7);
        dest.forEach(System.out::print);
        System.out.println();

        CollectionUtils.removeAll(dest, src);
        dest.forEach(System.out::print);
        System.out.println();
        CollectionUtils.addAll(src, dest);

        System.out.println(CollectionUtils.containsAll(dest, src));



        List<Integer> newList = CollectionUtils.newArrayList();
        newList.addAll(Arrays.asList(14, 10));
        dest.forEach(System.out::print);
        System.out.println();
        newList.forEach(System.out::print);
        System.out.println();
        System.out.println(CollectionUtils.containsAny(dest, newList));

        CollectionUtils.range(dest, 3, 7).forEach(System.out::print);
        System.out.println();

        CollectionUtils.range(dest, 3, 7, Comparator.naturalOrder()).forEach(System.out::print);
    }
}
