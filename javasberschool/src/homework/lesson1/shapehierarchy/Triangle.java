package homework.lesson1.shapehierarchy;

public class Triangle extends Shape {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    double calculateArea() {
        double p = calculatePerimeter()/2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}
