package com.sousa.matheus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String apiKey = "k_l53x30y4";

        System.out.println("Loading Top 250 movies...");

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
        String items = objectMapper.readTree(body).get("items").toString();
        List<Movie> movies =
                objectMapper.readValue(items, new TypeReference<List<Movie>>() {});

        PrintWriter writer = new PrintWriter("movies.html");
        HTMLGenerator htmlGenerator = new HTMLGenerator(writer);

        htmlGenerator.generate(movies);

        writer.close();

//        for (Movie movie : movies) {
//            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(Movie.class, Object.class).getPropertyDescriptors()) {
//                System.out.print(propertyDescriptor.getDisplayName() + ": ");
//                System.out.println(propertyDescriptor.getReadMethod().invoke(movie));
//            }
//            System.out.println("---------");
//        }

    }
}
