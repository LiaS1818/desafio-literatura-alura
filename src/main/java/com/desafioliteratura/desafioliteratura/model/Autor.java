package com.desafioliteratura.desafioliteratura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {

    @Id// indicar nuestro identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id auto incrmental
    private long Id;
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(String nombre, Integer nacimiento, Integer fallecimiento) {
        this.nombre = nombre;
        setNacimiento(nacimiento);
        setFallecimiento(fallecimiento);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    private void setNacimiento(Integer nacimiento) {
        this.nacimiento = (nacimiento == null) ? 0 : nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    private void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = (fallecimiento == null) ? 0 : fallecimiento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", fallecimiento=" + fallecimiento +
                '}';
    }
}
