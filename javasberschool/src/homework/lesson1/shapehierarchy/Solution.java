package homework.lesson1.shapehierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        Square square = new Square(4);
        Rect rect = new Rect(4, 6);
        Triangle triangle = new Triangle(4, 3, 2);
        Circle circle = new Circle(3);

        List<Shape> shapes = new ArrayList<>(Arrays.asList(square, rect, triangle, circle));

        System.out.println("Calculated Areas:");
        shapes.forEach(shape -> System.out.println(shape.getClass().getSimpleName() + ": " +
                        shape.calculateArea() + " mm" + (char)0x00B2 + "\n")
        );

        System.out.println("Calculated Perimeters:");
        shapes.forEach(shape -> System.out.println(shape.getClass().getSimpleName() + ": " +
                        shape.calculatePerimeter() + " mm")
        );
    }
}
