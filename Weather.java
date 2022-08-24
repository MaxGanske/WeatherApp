/*Maximilian Ganske
 * 5/17/2022
 * This program can find the current weather information of any city by providing 
 * the conditions of the sky, temperature, low temperature, high temperature, and humidity.
 * The program also shows a map of the city using Google Maps
 * 
 */
package testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather {
	
   boolean validCityName=false;
	
   static ArrayList<String> weatherInfo = new ArrayList<String>(); //Carries the weather info
	
   public static boolean cityWeather(String cityNameInput) throws Exception {	//cityWeather method
   
      try {
      
      //Weather API
         String firstPartURL = "https://api.openweathermap.org/data/2.5/weather?q=";
         String secondPartURL ="&appid=b45ceed3353e4d7cec5c44affeb5e444"; 
         String theURL = firstPartURL + cityNameInput + secondPartURL;
         URL url = new URL(theURL); 
      
         BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
      
         JSONParser jsonParser = new JSONParser();
      
         JSONObject myObject = (JSONObject)jsonParser.parse(br);
         JSONObject getMain = (JSONObject) myObject.get("main");
      
      //Adds the city name to weatherInfo
         weatherInfo.add(cityNameInput);
      
         JSONArray weatherArray = (JSONArray)myObject.get("weather");
         JSONObject w = (JSONObject) weatherArray.get(0);
         String getDes = w.get("description").toString();
      
      //Adds the sky's condition to weatherInfo
         weatherInfo.add(getDes);
      
         double cityTemp = (double)((JSONObject) getMain).get("temp"); //current temperature
      
         cityTemp = ((cityTemp - 273.15)*9)/5 + 32; //Converts to fahrenheit
         String tempNow = "temp: "+ String.format("%.1f", cityTemp)+"\u00B0";
         weatherInfo.add(tempNow); //Adds current temperature to weatherInfo
      
         double getTempMin = (double) getMain.get("temp_min"); //Min temperature
         double cityTempMin = getTempMin;
      
         cityTempMin = ((cityTempMin - 273.15) * 9) / 5 + 32; //Converts to fahrenheit
         String tempLow="low: "+String.format("%.1f", cityTempMin)+"\u00B0";
         weatherInfo.add(tempLow); //adds min temp to weatherInfo
      
         double getTempHigh = (double) getMain.get("temp_max"); //Max temperature
         double cityTempHigh = getTempHigh;
         cityTempHigh = ((cityTempHigh - 273.15) * 9) / 5 + 32; //Converts to fahrenheit
      
         String tempHigh = "high: "+String.format("%.1f", cityTempHigh)+"\u00B0";
         weatherInfo.add(tempHigh); //Adds max temperature to weatherInfo
      
         String getHumidity = getMain.get("humidity").toString(); //Humidity
         String cityHumidity = getHumidity;
         cityHumidity = "humidity: " + cityHumidity + "%";
      
         weatherInfo.add(cityHumidity); //Adds humidity to weatherInfo
      
         return true; //return true--meaning that the city name does exist
      
      }catch (Exception ex){ 
      
         return false; //City name is wrong; therefore, return false
      }
   }
   public static ArrayList<String> getCityWeatherNow(){
      return weatherInfo; //return weatherInfo
   }
	
	
}