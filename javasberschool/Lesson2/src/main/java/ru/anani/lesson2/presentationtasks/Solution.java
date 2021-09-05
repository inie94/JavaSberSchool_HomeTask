package ru.anani.lesson2.presentationtasks;

public class Solution {
    public static void main(String[] args) {
        String path = "D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\src\\homework\\lesson2\\presentationtasks\\source.txt";
        Content words = new Content(path);
        words.getSizeUniqueWords();
        words.getSortedUniqueWords();
        words.getWordsWithCount();
        words.getFileRowsFromEndToStart();

        words.getFileRowsFromEndToStartWithIterator();

        words.getRows(6, 3, 7);
    }
}
