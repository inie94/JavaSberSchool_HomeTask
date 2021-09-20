package ru.anani.lesson10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Path filePath = Paths.get("D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\Lesson10\\src\\main\\resources\\test.txt");

        List<Long> numericArray = getNumericArrayFromFile(filePath);

        numericArray.forEach(aLong -> new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + aLong +"! = " + factorial(aLong))).start());
    }

    private static List<Long> getNumericArrayFromFile(Path path) {
        List<Long> numericArray = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            while (reader.ready()) {
                numericArray.add(Long.parseLong(reader.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numericArray;
    }

    private static long factorial(long value) {
        long result = 1L;

        if (value == 0 || value == 1)
            return 1L;

        for (long i = 1; i <= value; i++) {
            result = result * i;
        }

        return result;
    }
}
