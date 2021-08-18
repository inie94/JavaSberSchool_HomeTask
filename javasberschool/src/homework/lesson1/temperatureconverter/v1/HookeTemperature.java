package homework.lesson1.temperatureconverter.v1;

public class HookeTemperature extends Temperature {

    private double value;

    public HookeTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return (12d/5d) * degreesCelsius;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HookeTemperature{" +
                "value=" + value + "°H}";
    }
}
