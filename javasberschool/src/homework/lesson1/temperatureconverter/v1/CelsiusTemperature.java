package homework.lesson1.temperatureconverter.v1;

public class CelsiusTemperature extends Temperature {

    private double value;

    public CelsiusTemperature(double value) {
        this.value = value;
    }

    @Override
    double convert(double degreesCelsius) {
        return degreesCelsius;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CelsiusTemperature{" +
                "value=" + value + "°C}";
    }
}
