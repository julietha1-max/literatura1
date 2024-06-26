package com.desafioliteratura.literatura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private String titulo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))

    @Column(name = "idioma")

       // @Fetch(FetchMode.JOIN)
    private List<String> idiomas;
@Column(nullable = false)
    private Integer descargas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "libro_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;
    public void addAutor(Autor autor){
        if (this.autores == null) {
            this.autores = new ArrayList<>();
        }
        this.autores.add(autor);

    }

    public void removeAutor(Autor autor) {
        this.autores.remove(autor);

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }


    }






