package ru.anani.lesson1.temperatureconverter.v1;

public class LeidenTemperature extends Temperature {

    private double value;

    public LeidenTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return degreesCelsius + 253;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LeidenTemperature{" +
                "value=" + value + "°L}";
    }
}
