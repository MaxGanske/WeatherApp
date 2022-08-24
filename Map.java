package testing;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Map {
	
   static String html;
   static String weather;
   static String mapFileName="myMap.html";
   static ArrayList<String> weatherInfo = new ArrayList<>();
	
   Map (ArrayList<String> weatherInfo, String mapType, int zoom) throws IOException {
      String city=weatherInfo.get(0);
   	     
   	   //Shows weatherInfo on top of the map
      weather =  " " + city.toUpperCase()+ "   | ";
      for(int i = 1; i < weatherInfo.size(); i++) {
         weather = weather + weatherInfo.get(i) + "   | ";
      }
   	   
      writeHTML(weather,city, mapType, zoom);
   	    
      String url = mapFileName;   
      File htmlFile = new File(url);
      Desktop.getDesktop().browse(htmlFile.toURI());
   }
	//HTML for the map page
   public static void writeHTML(String weatherNow, String city, String mapType, int zoom) {
      html="<!DOCTYPE html>"
         + "<html>"
         + "<body>"
         + "<h2>"
         + weatherNow
         + "</h2>"
         + "<iframe"
         + "  width=1200"
         + "  height=900"
         + "  style=border:0"
         + "  loading=lazy"
         + "  allowfullscreen"
         + "  referrerpolicy=\"no-referrer-when-downgrade\""
         + "src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyCxfLkM1-ITWE0J-3_mYypX0wQ2ZvSuKec&q="+ city +"&zoom="+ zoom 
         +"&maptype=" + mapType+"\""
         + "</iframe>"
         + "</body>"
         + "</html>";
      File f= new File (mapFileName);
      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter(f));
         bw.write(html);
         bw.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   	
   }
	

}



