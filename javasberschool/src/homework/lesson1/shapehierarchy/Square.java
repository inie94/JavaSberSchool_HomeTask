package homework.lesson1.shapehierarchy;

public class Square extends Shape{

    private double height;

    public Square(double height) {
        this.height = height;
    }

    @Override
    double calculatePerimeter() {
        return height * 2;
    }

    @Override
    double calculateArea() {
        return Math.pow(height, 2);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
