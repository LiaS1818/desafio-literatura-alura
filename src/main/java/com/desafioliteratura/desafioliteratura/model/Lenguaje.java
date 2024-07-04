package com.desafioliteratura.desafioliteratura.model;

import java.util.List;

public enum Lenguaje {
    ESPANOL("es", "Español"),
    INGLES("en", "Inglés"),
    FRANCES("fr", "Francés"),
    JAPONES("jp", "Japonés"),
    PORTUGUES("pt", "Portugués");

    private String categoriaGutendex;
    private String categoriaEspanol;

    Lenguaje(String categoriaGutendex, String categoriaEspanol){
        this.categoriaGutendex = categoriaGutendex;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Lenguaje fromString(String text) {
        for(Lenguaje lenguaje : Lenguaje.values()){
            if(lenguaje.categoriaGutendex.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada" + text);
    }

    public static Lenguaje fromEspanol(String text) {
        for(Lenguaje categoria : Lenguaje.values()){
            if(categoria.categoriaEspanol.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada" + text);
    }
}
