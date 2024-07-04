package com.desafioliteratura.desafioliteratura.repository;

import com.desafioliteratura.desafioliteratura.model.Lenguaje;
import com.desafioliteratura.desafioliteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByLenguaje(Lenguaje lenguaje);

}
