package homework.lesson1.temperatureconverter.v1;

public class FahrenheitTemperature extends Temperature {

    private double value;

    public FahrenheitTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return (9d/5d) * degreesCelsius + 32;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FahrenheitTemperature{" +
                "value=" + value + "°F}";
    }
}
