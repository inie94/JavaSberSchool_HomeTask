package homework.lesson1.temperatureconverter;

public class RomerTemperature extends Temperature {

    private double value;

    public RomerTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return (21d/40d) * degreesCelsius + 7.5;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RomerTemperature{" +
                "value=" + value + "°Rø}";
    }
}
