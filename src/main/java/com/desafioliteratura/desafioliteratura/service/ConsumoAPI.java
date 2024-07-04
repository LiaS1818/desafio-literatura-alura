package com.desafioliteratura.desafioliteratura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url){
        System.out.println("URL a consultar: " + url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de respuesta: " + response.statusCode());
        } catch (IOException e) {
            throw new RuntimeException("IOException al enviar la solicitud: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("InterruptedException al enviar la solicitud: " + e.getMessage());
        }

        String json = response.body();
        System.out.println("Respuesta JSON recibida: " + json);

        return json;
    }
}

