package ru.anani.lesson7.task1;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        PluginManager pluginManager = new PluginManager("D:/anani/IdeaProjects/JavaSberSchool_HomeTask/javasberschool/Lesson7/plugins");
        System.out.println("Init");
        pluginManager.initializePlugin();
        pluginManager.startAll();

    }
}
