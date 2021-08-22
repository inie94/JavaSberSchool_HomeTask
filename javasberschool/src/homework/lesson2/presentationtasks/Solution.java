package homework.lesson2.presentationtasks;

public class Solution {
    public static void main(String[] args) {
        String path = "D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\src\\homework\\lesson2\\presentationtasks\\source.txt";
        UniqueWords words = new UniqueWords(path);
        words.getSizeUniqueWords();
        words.getSortedUniqueWords();
        words.getWordsWithCount();
        words.getFileRowsFromEndToStart();

        words.getFileRowsFromEndToStartWithIterator();

        words.getRows(6, 3, 7);
    }
}
