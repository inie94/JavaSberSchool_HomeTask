package homework.lesson1.temperatureconverter;

public class KelvinTemperature extends Temperature{

    private double value;

    public KelvinTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return degreesCelsius + 273.15;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KelvinTemperature{" +
                "value=" + value +
                " K}";
    }
}
