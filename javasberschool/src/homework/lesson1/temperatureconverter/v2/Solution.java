package homework.lesson1.temperatureconverter.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Request form: <value> -> <unit>");
            System.out.println("Enabled units: C, K, F, Da, D, H, L, N, Ra, R, Ro.");
            String request = "";
            while (true) {
                System.out.print("Write request:");
                request = reader.readLine();
                if (request.equals("exit")) {
                    break;
                }
                request = request.replaceAll("[.,]", ".");
                Pattern pattern = Pattern.compile("\\d+[.]*\\d+\\s\\Q->\\E\\s[A-Z]");
                Matcher matcher = pattern.matcher(request);
                if(matcher.find()) {
                    String[] data = request.split("\\s");
                    double value;
                    switch (data[2]) {
                        case "C":
                            System.out.println("Convert result: " + data[0] + "°C");
                            break;
                        case "K":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToKelvin(value) + "K");
                            break;
                        case "F":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToFahrenheit(value) + "°F");
                            break;
                        case "Da":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToDalton(value) + "°Da");
                            break;
                        case "D":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToDelisle(value) + "°D");
                            break;
                        case "H":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToHooke(value) + "°H");
                            break;
                        case "L":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToLeiden(value) + "°L");
                            break;
                        case "N":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToNewton(value) + "°N");
                            break;
                        case "Ra":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToRankine(value) + "°Ra");
                            break;
                        case "R":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToReaumur(value) + "°R");
                            break;
                        case "Ro":
                            value = Double.parseDouble(data[0]);
                            System.out.println("Convert result: " + Temperature.convertToRomer(value) + "°Rø");
                            break;
                        default:
                            System.out.println("Unit is wrong, please write unit like: C, K, F, Da, D, H, L, N, Ra, R, Ro.\n" +
                                    "Write \"exit\" for quit.");
                    }
                } else {
                    System.out.println("Your request id wrong, please write request like request form.");
                }
            }
            System.out.println("Thanks for using, goodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
