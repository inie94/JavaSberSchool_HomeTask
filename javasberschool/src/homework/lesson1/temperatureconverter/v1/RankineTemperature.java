package homework.lesson1.temperatureconverter.v1;

public class RankineTemperature extends Temperature {

    private double value;

    public RankineTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        KelvinTemperature kelvinTemperature = new KelvinTemperature(degreesCelsius);
        return kelvinTemperature.getValue() * 1.8;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RankineTemperature{" +
                "value=" + value + "°Ra}";
    }
}
