package homework.lesson2.presentationtasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UniqueWords {

    private String path;
    private List<String> fileContent;

    public UniqueWords(String path) {
        this.path = path;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> fileContent = new ArrayList<>();
            while (reader.ready()) {
                fileContent.add(reader.readLine());
            }
            this.fileContent = fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> fileContent = new ArrayList<>();
            while (reader.ready()) {
                fileContent.add(reader.readLine());
            }
            this.fileContent = fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getFileContent() {
        return fileContent;
    }

    private Set<String> getUniqueWords() {
        Set<String> uniqueWords = new HashSet<>();
        fileContent.forEach(s -> {
            s = s.replaceAll("\\p{Punct}", "");
            Collections.addAll(uniqueWords, s.trim().split("\\s"));
        });

        return uniqueWords;
    }

    public void getSizeUniqueWords() {
        System.out.println("Count unique words: " + getUniqueWords().size());
    }

    public void getSortedUniqueWords() {
        System.out.println("\nSorted unique words with case: ");
        getUniqueWords().stream()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .forEach(System.out::println);
        System.out.println("\n Sorted unique words ignore case: ");
        getUniqueWords().stream()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareToIgnoreCase))
                .forEach(System.out::println);
    }

    public void getWordsWithCount() {
        Map<String, Integer> words = new HashMap<>();
        fileContent.forEach(s -> {
            s = s.replaceAll("\\p{Punct}", "");
            for (String word: s.trim().split("\\s")) {
                Integer count = words.get(word);
                if (count == null) {
                    count = 0;
                }
                words.put(word, ++count);
            }
        });

        System.out.println("\nWords with count: ");
        words.forEach((s, integer) -> System.out.println("Word: \"" + s + "\"," + " Count: " + integer + ";"));
    }

    public void getFileRowsFromEndToStart() {
        Deque<String> rows = new ArrayDeque<>();
        fileContent.forEach(rows::addFirst);
        System.out.println("\nRows from end to start file: ");
        while (!rows.isEmpty()) {
            System.out.println(rows.pop());
        }
    }
    
    public void getRows (int... indexes) {
        System.out.print("\nGet rows with indexes: ");
        for(int i:indexes){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i: indexes) {
            System.out.println(i + ": " + fileContent.get(i));
        }
    }

    public void getFileRowsFromEndToStartWithIterator() {
        System.out.println("\nRows from end to start file with custom iterator:");
        Iterator<String> iterator = new ReverseIterator<>();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    class ReverseIterator<E> implements Iterator<E> {

        int cursor;
        int lastRet = UniqueWords.this.fileContent.size();

        ReverseIterator() {
            this.cursor = UniqueWords.this.fileContent.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i < 0)
                throw new NoSuchElementException();
            cursor = i - 1;
            return (E) UniqueWords.this.fileContent.toArray()[lastRet = i];
        }
    }
}
