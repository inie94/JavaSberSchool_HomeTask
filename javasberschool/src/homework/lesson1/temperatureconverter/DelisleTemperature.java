package homework.lesson1.temperatureconverter;

public class DelisleTemperature extends Temperature {

    private double value;

    public DelisleTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return (100 - degreesCelsius) * (3d/2d);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DelisleTemperature{" +
                "value=" + value + "°D}";
    }
}
