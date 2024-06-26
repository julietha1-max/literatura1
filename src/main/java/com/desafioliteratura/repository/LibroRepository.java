package com.desafioliteratura.repository;

import com.desafioliteratura.literatura.model.Autor;
import com.desafioliteratura.literatura.model.Libro;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {


    List<Libro> findByIdiomasContaining(String idioma);
}



