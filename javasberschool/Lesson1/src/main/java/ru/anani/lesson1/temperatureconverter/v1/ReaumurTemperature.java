package ru.anani.lesson1.temperatureconverter.v1;

public class ReaumurTemperature extends Temperature {

    private double value;

    public ReaumurTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return degreesCelsius / 1.25;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    public String toString() {
        return "ReaumurTemperature{" +
                "value=" + value + "°R}";
    }
}
