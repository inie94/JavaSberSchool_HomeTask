package homework.lesson1.shapehierarchy;

public class Rect extends Square {

    private double width;

    public Rect(double width, double height) {
        super(height);
        this.width = width;
    }

    @Override
    double calculatePerimeter() {
        return this.getHeight() + width;
    }

    @Override
    double calculateArea() {
        return this.getHeight() * width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
