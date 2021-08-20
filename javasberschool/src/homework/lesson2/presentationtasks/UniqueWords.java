package homework.lesson2.presentationtasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UniqueWords {
    public static void main(String[] args) {
        try {
            String path = "D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\src\\homework\\lesson2\\presentationtasks\\source.txt";
            getSizeUniqueWords(path);
            getSortedUniqueWords(path);
            getWordsWithCount(path);
            getFileRowsFromEndToStart(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSizeUniqueWords(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Set<String> uniqueWords = new HashSet<>();
            while (reader.ready()) {
                String s = reader.readLine();
                s = s.replaceAll("\\p{Punct}", "");
                for (String word: s.trim().split("\\s")) {
                    uniqueWords.add(word);
                }
            }
            System.out.println("Count unique words: " + uniqueWords.size());
        }
    }

    public static void getSortedUniqueWords(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Set<String> uniqueWords = new HashSet<>();
            while (reader.ready()) {
                String s = reader.readLine();
                s = s.replaceAll("\\p{Punct}", "");
                for (String word: s.trim().split("\\s")) {
                    uniqueWords.add(word);
                }
            }
            System.out.println("\nSorted unique words with case: ");
            uniqueWords.stream()
                    .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                    .forEach(s -> System.out.println(s));
            System.out.println("\n Sorted unique words ignore case: ");
            uniqueWords.stream()
                    .sorted(Comparator.comparing(String::length).thenComparing(String::compareToIgnoreCase))
                    .forEach(s -> System.out.println(s));
        }
    }

    public static void getWordsWithCount(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int count = 0;
            Map<String, Integer> words = new HashMap<>();
            while (reader.ready()) {
                String s = reader.readLine();
                s = s.replaceAll("\\p{Punct}", "");
                for (String word: s.trim().split("\\s")) {
                    if (words.containsKey(word)) {
                        words.put(word, ++count);
                    } else {
                        count = 0;
                        words.put(word, ++count);
                    }
                }
            }
            System.out.println("\nWords with count: ");
            words.forEach((s, integer) -> System.out.println("Word: \"" + s + "\"," + " Count: " + integer + ";"));
        }
    }

    public static void getFileRowsFromEndToStart(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Deque<String> rows = new ArrayDeque<>();
            while (reader.ready()) {
                rows.addFirst(reader.readLine());
            }
            System.out.println("\nRows from end to start file: ");
            while (!rows.isEmpty()) {
                System.out.println(rows.pop());
            }
        }
    }
}
