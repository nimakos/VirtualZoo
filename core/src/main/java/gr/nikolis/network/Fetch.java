package gr.nikolis.network;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class Fetch {

    /**
     * Make an api call
     *
     * @param url    The url
     * @param header The header
     * @return The string value
     * @throws IOException ...
     * @throws InterruptedException ...
     */
    public String restApiCall(String url, String... header) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(header)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
