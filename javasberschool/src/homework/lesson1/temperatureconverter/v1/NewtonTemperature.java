package homework.lesson1.temperatureconverter.v1;

public class NewtonTemperature extends Temperature {

    public double value;

    public NewtonTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        return (33d/100d) * degreesCelsius;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NewtonTemperature{" +
                "value=" + value + "°N}";
    }
}
