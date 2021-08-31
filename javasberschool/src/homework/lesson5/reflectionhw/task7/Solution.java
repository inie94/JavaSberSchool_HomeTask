package homework.lesson5.reflectionhw.task7;

import homework.lesson5.reflectionhw.task2.B;

public class Solution {
    public static void main(String[] args) {
        B b1 = new B();
        B b2 = new B();
        b2.setField1("1");
        b2.setField2("2");
        b2.setField3("3");
        BeanUtils.assign(b1, b2);
        System.out.println(b1.getField1());
        System.out.println(b1.getField2());
        System.out.println(b1.getField3());
    }
}
