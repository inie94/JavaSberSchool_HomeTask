package homework.lesson1.temperatureconverter.v2;

public class Temperature {

    static double convertToKelvin(double degreesCelsius) {
        return degreesCelsius + 273.15;
    }

    static double convertToFahrenheit(double degreesCelsius) {
        return (9d/5d) * degreesCelsius + 32;
    }

    static double convertToDalton(double degreesCelsius) {
        return 100 * ((Math.log10(convertToKelvin(degreesCelsius)) - Math.log10(273.15)) /
                (Math.log10(373.15) - Math.log10(273.15)));
    }

    static double convertToDelisle(double degreesCelsius) {
        return (100 - degreesCelsius) * (3d/2d);
    }

    static double convertToHooke(double degreesCelsius) {
        return (12d/5d) * degreesCelsius;
    }

    static double convertToLeiden(double degreesCelsius) {
        return degreesCelsius + 253;
    }

    static double convertToNewton(double degreesCelsius) {
        return (33d/100d) * degreesCelsius;
    }

    static double convertToRankine(double degreesCelsius) {
        return convertToKelvin(degreesCelsius) * 1.8;
    }

    static double convertToReaumur(double degreesCelsius) {
        return degreesCelsius / 1.25;
    }

    static double convertToRomer(double degreesCelsius) {
        return (21d/40d) * degreesCelsius + 7.5;
    }

}
