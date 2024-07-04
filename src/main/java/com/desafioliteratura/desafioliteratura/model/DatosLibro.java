package com.desafioliteratura.desafioliteratura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro (
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer totalDescargas
) {
        // Métodos getters y setters generados automáticamente por los records

        public String titulo() {
                return titulo;
        }

        public List<DatosAutor> getAutores() {
                return autores;
        }



        // Método para obtener los idiomas como una cadena separada por comas
        public String languagesAsString() {
                return String.join(",", languages);
        }
}


