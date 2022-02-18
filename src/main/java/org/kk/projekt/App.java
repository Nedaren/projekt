package org.kk.projekt;

import javax.swing.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
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
//        fetchfromMeteoApi(fetchfromGeoApi());
        var frame = new MainFrame(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

//    private void fetchfromApi() {
//        try {
//            ApiClient apiClient = new ApiClient("http://api.zpsb.alyx.pl/json/?delay=10");
//            apiClient.fetch();
//            ApiResponse response = apiClient.getresponse();
//            if (response != null) {
//                System.out.printf("Author: %s%n", response.author);
//                System.out.printf("Quote: %s%n", response.text);
//            }
//            else {
//                System.out.printf("Error: %s%n", apiClient.getError());
//            }
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

    public GeoApiResponse fetchfromGeoApi() {
        GeoApiResponse response = null;
        try {
            GeoApiClient apiClient = new GeoApiClient("https://freegeoip.app/json/");

            apiClient.fetch();
            response = apiClient.getresponse();
            if (response != null) {
//                System.out.printf("Current Time: " + localDateTime + "%n");
//                System.out.printf("Hour: " + hour + "%n");
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

    public CurrentWeather fetchfromMeteoApi(GeoApiResponse geo) {
        try {
            String url = String.format("https://www.7timer.info/bin/meteo.php?ac=0&unit=metric&output=json&lon=%s&lat=%s", geo.longitude, geo.latitude);
            MeteoApiClient apiClient = new MeteoApiClient(url);
            LocalDateTime localDateTime = LocalDateTime.now();
            int hour = localDateTime.getHour();
            apiClient.fetch();
            MeteoApiResponse response = apiClient.getresponse();
            CurrentWeather weather = new CurrentWeather(response);
            return weather;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
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
