package ru.anani.lesson8.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class DeserializeSolution {
    public static void main(String[] args) {
        Object o = null;
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\Lesson8\\src\\main\\resources\\test.properties"))) {

            o = stream.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(o != null)
            Arrays.stream((String[]) o).forEach(s -> System.out.println(s));
        else
            System.out.println(o);
    }
}
