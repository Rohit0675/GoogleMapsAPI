import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleMapsAPI {

    public static void main(String[] args) {
        String address = "Sukhsagar Nagar, Katraj"; 
        String apiKey = "AIzaSyBCiMR8LHK34EG5IAiEDA8vkphlGKD_ZIU"; 

        try {
            String geocodingResponse = getGeocodingResponse(address, apiKey);
            System.out.println("Geocoding Response:");
            System.out.println(geocodingResponse);

            
            double[] coordinates = parseGeocodingResponse(geocodingResponse);
            if (coordinates != null) {
                double latitude = coordinates[0];
                double longitude = coordinates[1];
                System.out.println("\nLatitude: " + latitude);
                System.out.println("Longitude: " + longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getGeocodingResponse(String address, String apiKey) throws IOException {
        address = URLEncoder.encode(address, "UTF-8");
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        URL apiUrl = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    private static double[] parseGeocodingResponse(String jsonResponse) {
        

        double[] coordinates = new double[2];

        int startIndex = jsonResponse.indexOf("\"lat\"") + 6;
        int endIndex = jsonResponse.indexOf(",", startIndex);
        coordinates[0] = Double.parseDouble(jsonResponse.substring(startIndex, endIndex));

        startIndex = jsonResponse.indexOf("\"lng\"") + 6;
        endIndex = jsonResponse.indexOf("}", startIndex);
        coordinates[1] = Double.parseDouble(jsonResponse.substring(startIndex, endIndex));

        return coordinates;
    }
}
