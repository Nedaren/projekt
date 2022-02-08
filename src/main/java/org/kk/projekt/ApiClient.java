package org.kk.projekt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {

    private final URI uri;
    private ApiResponse response;
    private String error;

    public ApiClient(String url) throws URISyntaxException {
        this.error = "";
        this.uri = new URI(url);
    }

    public ApiResponse getresponse() {
        return this.response;
    }

    public String getError() {
        return this.error;
    }

    public void fetch() {
        this.error = "";
        this.response = null;

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(6))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(this.uri)
                .headers("Accept", "application/json")
                .timeout(Duration.ofMillis(5001))
                .build();

        String json = null;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
        } catch (IOException e) {
            this.error = e.getMessage();
           // e.printStackTrace();
        } catch (InterruptedException e) {
            this.error = e.getMessage();
            //e.printStackTrace();
        }

        if (json == null) {
            return;
        }

        Gson gson = new GsonBuilder().create();
        this.response = gson.fromJson(json, ApiResponse.class);

//        System.out.println(json);
    }
}
