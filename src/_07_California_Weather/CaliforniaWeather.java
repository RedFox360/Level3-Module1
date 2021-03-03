package _07_California_Weather;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 *          
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api 
 */

public class CaliforniaWeather {
    
    void start() {
        HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
        	
        // All city keys have the first letter capitalized of each word
        String options[] = {"Get the weather in a city", "Find cities with a weather condition", "Find cities with a minimum and maximum temperature"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an Option", null, 1, 1, null, options,0);
        if (choice == 0) {
            String cityName = Utilities.capitalizeWords(JOptionPane.showInputDialog("Enter a city"));
            WeatherData datum = weatherData.get(cityName);
            if (datum == null) {
                JOptionPane.showMessageDialog(null, "Unable to find weather data for: " + cityName);
            } else {
            	DecimalFormat df = new DecimalFormat("#.##");
            	double celsius = Double.parseDouble(df.format((datum.temperatureF-32)*0.5556));
            	JOptionPane.showMessageDialog(null, cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + "°F or " + celsius + "°C");
            }
        }
        else if (choice == 1) {
            String weatherCondition = Utilities.capitalizeWords(JOptionPane.showInputDialog("Enter a weather condition (Mostly Cloudy, Clear, Overcast, Partly Cloudy)"));
            ArrayList<String> data = new ArrayList<>();
            for (String w : weatherData.keySet()) {
            	if (weatherData.get(w).weatherSummary.equals(weatherCondition)) {
            		data.add(w);
            	}
            }
            String msg = "Cities with Weather Condition " + weatherCondition + ": ";
            for (String d : data) {
            	msg += d + ", ";
            }
            JOptionPane.showMessageDialog(null, msg);
        }
        else if (choice == 2) {
        	double min = Double.parseDouble(JOptionPane.showInputDialog("Enter the minimum value"));
        	double max = Double.parseDouble(JOptionPane.showInputDialog("Enter the maximum value"));
        	ArrayList<String> data = new ArrayList<>();
        	for (String w : weatherData.keySet()) {
        		if (weatherData.get(w).temperatureF < max && weatherData.get(w).temperatureF > min) {
        			data.add(w);
        		}
        	}
        	 String msg = "Cities with temperatures between " + min + " and " + max + ": ";
             for (String d : data) {
             	msg += d + ", ";
             }
             JOptionPane.showMessageDialog(null, msg);
        }
    }
}
