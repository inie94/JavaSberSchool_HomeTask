package ru.anani.lesson8.tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeSolution {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("first", "second", "third");

//        System.out.println(list.toString());

        Object o = new String[]{"first", "second", "third"};
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\Lesson8\\src\\main\\resources\\test.properties"))) {
            stream.writeObject(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
