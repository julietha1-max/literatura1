package com.desafioliteratura.literatura.principal;

import com.desafioliteratura.literatura.model.Autor;
import com.desafioliteratura.literatura.model.GutendexResponse;

import com.desafioliteratura.literatura.model.Libro;
import com.desafioliteratura.literatura.service.ConsumoAPI;
import com.desafioliteratura.literatura.service.ConvierteDatos;
import com.desafioliteratura.repository.AutorRepository;
import com.desafioliteratura.repository.LibroRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final  String  URL_BASE="http://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
private final LibroRepository libroRepository;
private final AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository=autorRepository;
    }


   public void muestraElMenu() {
       var opcion = -1;
       while (opcion != 0){

           var menu = """
       ***************************
       
       Elija la opcion atraves de su numero.
       1- Buscar libro por titulo.
       2- Listar libros registrados. 
       3- Listar autores registrados.
       4- Listar autores vivos en un determinado año.
       5- Listar libros por idioma.
       0- Salir. 
     """;
           System.out.println(menu);
           opcion= teclado.nextInt();
           teclado.nextLine();
           switch (opcion){
               case 1:
                   buscarLibroTitulo();
                   break;
               case 2:
                   buscarLibrosRegistrados();
                   break;
               case 3:
                   buscarAutoresRegistrados();
                   break;
               case 4:
                   buscarAutoresVivosEnAnio();
                   break;
               case 5:
                   buscarLibrosPorIdioma();
                   break;
               case 0:
                   System.out.println("Cerrando la aplicacion...");
                   break;
               default:
                   System.out.println("Opcion invalida");
           }
       }}

    private void buscarLibroTitulo() {

        System.out.println("Escribe el libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        String encodedNombreLibro = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8);
        String url = URL_BASE + encodedNombreLibro;
        var json = consumoAPI.obtenerDatos(url);
        GutendexResponse response = conversor.obtenerDatos(json, GutendexResponse.class);


        System.out.println("Cantidad de libros encontrados: " + response.getCount());

        for (GutendexResponse.GutendexBook book : response.getResults()) {
            Libro libro = new Libro();
            String tituloRecibidoDesdeAPI = book.getTitle();
            if (tituloRecibidoDesdeAPI.length() > 255) {
                libro.setTitulo(tituloRecibidoDesdeAPI.substring(0, 255));
            } else {
                libro.setTitulo(tituloRecibidoDesdeAPI);
            }

            libro.setIdiomas(Collections.singletonList(String.join(", ", book.getLanguages())));
            libro.setDescargas(book.getDownload_count());

           /*Libro libro = new Libro();
            libro.setTitulo(book.getTitle());
           libro.setIdiomas(Collections.singletonList(String.join(", ", book.getLanguages())));
            libro.setDescargas(book.getDownload_count());*/
            List<Autor> autores = new ArrayList<>();
            for (GutendexResponse.GutendexAuthor author : book.getAuthors()) {
                Autor autor = autorRepository.findByNombre(author.getName());
                if (autor == null) {
                    autor = new Autor();
                    autor.setNombre(author.getName());
                    autor.setAnioNacimiento(author.getBirth_year());
                    autor.setAnioMuerte(author.getDeath_year());
                    autorRepository.save(autor);
                }
                autores.add(autor);
            }

            libro.setAutores(autores);
            this.libroRepository.save(libro);

            System.out.println("--------libro-------");
            System.out.println("Título: " + book.getTitle());
            System.out.println("Idiomas: " + String.join(", ", book.getLanguages()));
            System.out.println("Descargas: " + book.getDownload_count());
            System.out.println("Autores:");
            for (GutendexResponse.GutendexAuthor author : book.getAuthors()) {
                System.out.println("- " + author.getName() );
            }

            System.out.println();
        }}
           private void buscarLibrosRegistrados(){List<Libro> libros = libroRepository.findAll();
               if (libros.isEmpty()) {
                   System.out.println("No hay libros registrados.");
               } else {
                   System.out.println("Libros registrados:");
                   for (Libro libro : libros) {
                       System.out.println("Título: " + libro.getTitulo());
                       System.out.println("Idiomas: " + String.join(", ", libro.getIdiomas()));
                       System.out.println("Descargas: " + libro.getDescargas());
                       System.out.println("Autores:");
                       for (Autor autor : libro.getAutores()) {
                           System.out.println("- " + autor.getNombre() + " (" + autor.getAnioNacimiento() + "-" + autor.getAnioMuerte() + ")");
                       }
                       System.out.println("--------");
                   }}}
              private void  buscarAutoresRegistrados()
                  { List<Autor>autores= autorRepository.findAll();


                      if (autores.isEmpty()) {
                          System.out.println("No hay autores registrados.");
                      } else {
                          System.out.println("Autores registrados:");
                          for (Autor autor : autores) {
                              System.out.println("- " + autor.getNombre() + " (Nacido en " + autor.getAnioNacimiento() + ", Fallecido en " + autor.getAnioMuerte() + ")");
                          }
                      }
                  }


    private void buscarAutoresVivosEnAnio() {
        System.out.println("Ingresa el año para buscar autores vivos:");
        int anio = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea

        List<Autor> autoresVivos = autorRepository.findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(anio, anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            for (Autor autor : autoresVivos) {
                System.out.println("- " + autor.getNombre() + " (Nacido en " + autor.getAnioNacimiento() + ", Fallecido en " + autor.getAnioMuerte() + ")");
            }
        }
    }



    private void buscarLibrosPorIdioma() {
       System.out.println("Ingrese el idioma para buscar los libros:");
        System.out.println( "es-español")  ;
        System.out.println( "en-ingles");
        System.out.println( "fr-frances");
        System.out.println( "pt-portugues");



        String idioma = teclado.nextLine();

        List<Libro> librosEnIdioma = libroRepository.findByIdiomasContaining(idioma);

        if (librosEnIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + idioma);
        } else {
            System.out.println("Libros en el idioma " + idioma + ":");
            for (Libro libro : librosEnIdioma) {
                System.out.println("- " + libro.getTitulo());
                System.out.println("  Idiomas: " + String.join(", ", libro.getIdiomas()));
                System.out.println("  Descargas: " + libro.getDescargas());
                System.out.println("  Autores:");
                for (Autor autor : libro.getAutores()) {
                    System.out.println("    - " + autor.getNombre());
                }
                System.out.println();
            }
        }}}
































