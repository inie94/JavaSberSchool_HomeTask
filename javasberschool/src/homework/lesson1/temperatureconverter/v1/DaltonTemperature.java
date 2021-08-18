package homework.lesson1.temperatureconverter.v1;

public class DaltonTemperature extends Temperature {

    private double value;

    public DaltonTemperature(double degreesCelsius) {
        this.value = convert(degreesCelsius);
    }

    @Override
    double convert(double degreesCelsius) {
        KelvinTemperature kelvinTemperature = new KelvinTemperature(degreesCelsius);
        return 100 * ((Math.log10(kelvinTemperature.getValue()) - Math.log10(273.15)) /
                (Math.log10(373.15)) - Math.log10(273.15));
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DaltonTemperature{" +
                "value=" + value + "°Da}";
    }
}
