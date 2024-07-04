package com.desafioliteratura.desafioliteratura.repository;

import com.desafioliteratura.desafioliteratura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombreAutor);

    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :fecha AND (a.fallecimiento IS NULL OR a.fallecimiento >= :fecha)")
    List<Autor> autoresVivosPorFecha(@Param("fecha")int fecha);
}
