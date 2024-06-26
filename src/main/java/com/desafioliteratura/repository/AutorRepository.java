package com.desafioliteratura.repository;

import com.desafioliteratura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor>findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(Integer anioNacimiento, Integer anioMuerte);
        Autor findByNombre(String nombre);
    }

