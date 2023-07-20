#Java Program to fetch location Information using Google Maps API
This java program fetches the location information for a given address using the Google Maps API and displays the latitude and longitude of that address.
I have imported necessary classes and packages for handling HTTP connections and IO operations.
The "address" and "apiKey" as parameters and returns the JSON response from the Google Maps Geocoding API. It uses HTTPURLConnection to make a GET request to the API and reads the response.
The getGeocodingResponse method is used for sending a request to the Google Maps API with the address and API key, and then getting the response back. 
The parseGeocodingResponse method takes the JSON response as input and extracts the latitude and longitude values from it. 
I called the parseGeocodingResponse method to get the latitude and longitude values from the JSON response and I have printed them on the terminal.
