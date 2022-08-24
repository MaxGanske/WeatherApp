package testing;

import java.util.*;

public class WeatherApp {
	
   static Scanner consol = new Scanner(System.in);
   private static ArrayList<String> weatherInfo = new ArrayList<>();
   static String mapType;
   static int zoom;
	 
   public static void main(String[] args) throws Exception {
      System.out.println("Welcome to WeatherApp");
      System.out.println();
     
      inputCityName(); //Gets user's info
      getWeatherInfo(mapType, zoom); //Shows map
      
   }
   public static void inputCityName() throws Exception{     
      boolean validCityName=false; 
      while (!validCityName) {
      
         System.out.println("Input a city name:");
         String city = consol.nextLine(); //Get City name
         System.out.println();
         System.out.println("Select map type: 1) roadmap 2) satellite");
         int mapChoice = consol.nextInt();
         if(mapChoice == 1) { //If 1 then roadmap
            mapType = "roadmap";
         } 
         if(mapChoice == 2) { //If 2 then satellite
            mapType = "satellite";
         } 
         System.out.println("Select zoom level of the map: 0 ~ 21 (default = 14");
         int zoomChoice = consol.nextInt();
         if (zoomChoice >= 0 || zoomChoice <= 21) { //If number between 0 and 21 set it to zoom
            zoom = zoomChoice;
         } else { //if not then set the default number
            System.out.println("Your number was either too large or too small. The default number was applied");
            zoom = 14;
         }
       
         boolean valid = Weather211.cityWeather(city);
       
         if (valid) { 
           
            System.out.println("Current Weather [" + city +"]\n");
            
            break;
         } else { //If city name is not right
            System.out.println("Invalid city name. Type again.\n");  
         }  
      }   
   }
   public static void getWeatherInfo(String mapType, int zoom) throws Exception { 
      weatherInfo = Weather211.getCityWeatherNow();
   	 
   	   //Prints weather info to console
      for (int i=0; i<weatherInfo.size(); i++) {
         System.out.println(weatherInfo.get(i));
      }
   	 
   	  //Shows map
      new Map(weatherInfo, mapType, zoom);
   }
}