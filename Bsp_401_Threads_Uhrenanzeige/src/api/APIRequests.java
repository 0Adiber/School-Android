package api;


import java.net.*;
import java.io.*;
import java.text.Normalizer;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class APIRequests {
    
    private static final String API_KEY = "AIzaSyD4Vnz5KrhyZozt7KiYWm7Bprrtj5DGrK8";    
    
    public static String[] getLocation(String input) throws IOException {
        URL urlForGetRequest = new URL("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=" + API_KEY + "&inputtype=textquery&fields=geometry&input=" + input);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result

            String res = response.toString();
            res = res.substring(res.indexOf("lat")+6, res.indexOf("}")).replace(" ", "").replace("\"", "").replace("lng:", "");
            
            if(res.contains("status")) {
                return new String[]{"GET NOT WORKED"};
            }
            
            return res.split(",");
        } else {
            return new String[]{"GET NOT WORKED"};
        }
    }
    
    public static String getTime(String lat, String lng) throws IOException {        
        URL urlForGetRequest = new URL("http://api.geonames.org/timezone?username=franz&lat=" + lat + "&lng=" + lng);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result

            String res = response.toString();
            
            res = res.substring(res.indexOf("<timezoneId>")+12, res.indexOf("</timezoneId>"));
            
            return res;
        } else {
            return "GET NOT WORKED";
        }
    }
    
    public static String getTimeFromOrt(String ort) throws IOException {
        
        ort = ort.replace(" ", "_");
        
        String[] res = getLocation(ort);
        if(res[0].equalsIgnoreCase("get not worked")) {
            Set<String> zones = ZoneId.getAvailableZoneIds();
            for(String z : zones) {
                String[] parts = z.split("/");
                if(parts[parts.length-1].equalsIgnoreCase(ort))
                    return z;
            }
            throw new ZoneRulesException("The GET-Request did not work; Could also not find city in Java's ZoneIds");
        }
        return getTime(res[0], res[1]);
    }
    
    public static void main(String[] args) {
        
        try {
            String res = getTimeFromOrt("new york");
            System.out.println(res);
        } catch (IOException ex) {
            Logger.getLogger(APIRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
