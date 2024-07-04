package com.desafioliteratura.desafioliteratura.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.desafioliteratura.desafioliteratura.model.DatosLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumoAPIFull {
    private final ObjectMapper objectMapper;
    private final String baseUrl = "https://gutendex.com/books/?search=";

    public ConsumoAPIFull() {
        this.objectMapper = new ObjectMapper();
    }

    public List<DatosLibro> obtenerTodosLosDatos(String nombreLibro) throws URISyntaxException, InterruptedException {
        List<DatosLibro> todosLosDatos = new ArrayList<>();
        String nextUrl = baseUrl;

        //while (nextUrl != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(nextUrl + nombreLibro.toLowerCase().replace(" ", "+")))
                    .build();

            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String jsonData = response.body();
                    JsonNode jsonNode = objectMapper.readTree(jsonData);
                    String resultsJson = jsonNode.get("results").toString();

                    List<DatosLibro> libros = objectMapper.readValue(resultsJson, new TypeReference<List<DatosLibro>>() {});
                    todosLosDatos.addAll(libros);

                    // Revisar si hay siguiente página
                    nextUrl = jsonNode.has("next") ? jsonNode.get("next").asText() : null;
                } else {
                    System.out.println("Error al obtener datos de la URL: " + nextUrl + ". Código de respuesta: " + response.statusCode());

                }

            } catch (IOException e) {
                System.out.println("Error de E/S al procesar la respuesta de la URL: " + nextUrl + ". " + e.getMessage());
               // break;
            }
        //}

        return todosLosDatos;
    }
}
