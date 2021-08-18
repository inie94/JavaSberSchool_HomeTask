package homework.lesson1.temperatureconverter.v1;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        CelsiusTemperature celsiusTemperature = new CelsiusTemperature(300.00);
        DaltonTemperature daltonTemperature = new DaltonTemperature(300.00);
        DelisleTemperature delisleTemperature = new DelisleTemperature(300.00);
        FahrenheitTemperature fahrenheitTemperature = new FahrenheitTemperature(300.00);
        HookeTemperature hookeTemperature = new HookeTemperature(300.00);
        KelvinTemperature kelvinTemperature = new KelvinTemperature(300.00);
        LeidenTemperature leidenTemperature = new LeidenTemperature(300.00);
        NewtonTemperature newtonTemperature = new NewtonTemperature(300.00);
        RankineTemperature rankineTemperature = new RankineTemperature(300.00);
        ReaumurTemperature reaumurTemperature = new ReaumurTemperature(300.00);
        RomerTemperature romerTemperature = new RomerTemperature(300.00);

        List<Temperature> temperatureList = new ArrayList<>();

        temperatureList.add(celsiusTemperature);
        temperatureList.add(fahrenheitTemperature);
        temperatureList.add(kelvinTemperature);
        temperatureList.add(rankineTemperature);
        temperatureList.add(delisleTemperature);
        temperatureList.add(newtonTemperature);
        temperatureList.add(reaumurTemperature);
        temperatureList.add(romerTemperature);
        temperatureList.add(daltonTemperature);
        temperatureList.add(hookeTemperature);
        temperatureList.add(leidenTemperature);

        temperatureList.forEach(temperature -> System.out.println(temperature.toString()));

    }
}
