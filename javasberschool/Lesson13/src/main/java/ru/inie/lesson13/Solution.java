package ru.inie.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 150; i++) {
            String s = "newdsfasdf a";
            map.put(i, "value" + i);
        }
    }
}
