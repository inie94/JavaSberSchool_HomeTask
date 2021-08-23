package homework.lesson3.parametrizecountmap;

public class Solution {
    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        for (Object value:
             map.toMap().keySet()) {
            System.out.println(value + " " + map.getCount((Integer) value));
        }
    }
}
