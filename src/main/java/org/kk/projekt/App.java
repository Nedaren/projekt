package org.kk.projekt;

import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

public class App  implements Runnable {
    @Override
    public void run() {
//        boolean quit = false;
//        Scanner scanner = new Scanner(System.in);
//        do {
//            System.out.println("Menu: ");
//            System.out.println(" 1. Pobierz dane z API");
//            System.out.println(" 2. Wyjście z programu");
//            System.out.print("Wybór > ");
//            String input = scanner.nextLine();
//            switch (input) {
//                case "1":
//                    fetchfromApi();
//                    break;
//                case "2":
//                    quit = true;
//                    break;
//
//            }
//        } while (!quit);
//        scanner.close();
        fetchfromMeteoApi(fetchfromGeoApi());
    }

    private void fetchfromApi() {
        try {
            ApiClient apiClient = new ApiClient("http://api.zpsb.alyx.pl/json/?delay=10");
            apiClient.fetch();
            ApiResponse response = apiClient.getresponse();
            if (response != null) {
                System.out.printf("Author: %s%n", response.author);
                System.out.printf("Quote: %s%n", response.text);
            }
            else {
                System.out.printf("Error: %s%n", apiClient.getError());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private GeoApiResponse fetchfromGeoApi() {
        GeoApiResponse response = null;
        try {
            GeoApiClient apiClient = new GeoApiClient("https://freegeoip.app/json/");
            apiClient.fetch();
            response = apiClient.getresponse();
            if (response != null) {
                System.out.printf("City: %s%n", response.city);
                System.out.printf("Latitude: %s%n", response.latitude);
                System.out.printf("Longitude: %s%n", response.longitude);
            }
            else {
                System.out.printf("Error: %s%n", apiClient.getError());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            return response;
        }
    }

    private void fetchfromMeteoApi(GeoApiResponse geo) {
        try {
            String url = String.format("https://www.7timer.info/bin/meteo.php?ac=0&unit=metric&output=json&lon=%s&lat=%s", geo.longitude, geo.latitude);
            MeteoApiClient apiClient = new MeteoApiClient(url);
            apiClient.fetch();
            MeteoApiResponse response = apiClient.getresponse();
            if (response != null) {
                System.out.printf("Temp: %s%n", response.dataseries[0].temp2m);
                System.out.printf("Wind Speed: %s%n", response.dataseries[0].wind10m.speed);
            }
            else {
                System.out.printf("Error: %s%n", apiClient.getError());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    /*
    public Runnable Setup() throws Exception {
        Random random = new Random();
        boolean fail = random.nextInt() % 2 == 0;
        throw new Exception("Incjacja nie powiodła się.");
        return this;
    }
     */
}
