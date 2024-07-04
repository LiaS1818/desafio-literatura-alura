package com.desafioliteratura.desafioliteratura.principal;

import com.desafioliteratura.desafioliteratura.model.*;
import com.desafioliteratura.desafioliteratura.repository.AutorRepository;
import com.desafioliteratura.desafioliteratura.repository.LibroRepository;
import com.desafioliteratura.desafioliteratura.service.ConsumoAPIFull;
import com.desafioliteratura.desafioliteratura.service.ConvierteDatos;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPIFull consumoAPIFull = new ConsumoAPIFull();
    private final String URL_BASE = "https://gutendex.com/books/?page=1";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> libros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository libroRepositorio , AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraMenu() throws URISyntaxException, InterruptedException {
        var opcion = -1;

        while(opcion != 0){
            var menu = """
                    1 - Buscar libro
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarTituloLibroWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivosPorFecha();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicacion");
                break;
                default:
                    System.out.println("Opcion no valida");
            }

        }

    }



    private Libro getDatosLibro() throws URISyntaxException, InterruptedException {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escriba el nombre del libro que desea buscar");
        String nombreLibro = teclado.nextLine();

        List<DatosLibro> datosLibros = consumoAPIFull.obtenerTodosLosDatos(nombreLibro);

        Optional<DatosLibro> datosLibroOptional = datosLibros.stream()
                .filter(dl -> {
                    String[] palabrasClave = nombreLibro.toUpperCase().split("\\s+");
                    String tituloUpper = dl.titulo().toUpperCase();
                    return Arrays.stream(palabrasClave).allMatch(tituloUpper::contains);
                })
                .findFirst();

        if (datosLibroOptional.isPresent()) {
            DatosLibro datosLibro = datosLibroOptional.get();
            Libro libro = new Libro(datosLibro);

            // Obtener autores del libro encontrado
            List<Autor> autores = datosLibro.autores().stream()
                    .map(a -> new Autor(a.nombre(), a.anoNacimiento(), a.anoMuerte()))
                    .collect(Collectors.toList());

            // Asignar autores al libro
            libro.setAutores(autores);

            // Guardar los autores en la base de datos si no existen
            List<Autor> autoresPersistidos = libro.getAutores().stream()
                    .map(a -> {
                        Optional<Autor> autorExistente = autorRepositorio.findByNombre(a.getNombre());
                        return autorExistente.orElseGet(() -> autorRepositorio.save(a));
                    })
                    .collect(Collectors.toList());

            libro.setAutores(autoresPersistidos);  // Actualizar autores persistidos en el libro

            System.out.println("Autores registrados: ");
            autoresPersistidos.forEach(System.out::println);

            return libro;
        } else {
            System.out.println("Libro no encontrado: " + nombreLibro);
            return null;
        }
    }


    private void buscarTituloLibroWeb() throws URISyntaxException, InterruptedException {
        Libro libroNuevo = getDatosLibro();
        if (libroNuevo != null){
            //libros.add(libroNuevo);
            libroRepositorio.save(libroNuevo);
        }
    }



    private void mostrarLibrosBuscados(){
        libros = libroRepositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void buscarAutoresRegistrados(){
        autores = autorRepositorio.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void buscarLibrosRegistrados() {
        libros.forEach(System.out::println);
    }

    private void buscarAutoresVivosPorFecha() {
        System.out.println("Escribe el año");
        int anio = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autoresVivos = autorRepositorio.autoresVivosPorFecha(anio);
        autoresVivos.forEach(System.out::println);
    }

    private void buscarLibroPorIdioma() {
        System.out.println("Escriba el idioma que quiere buscar");
        var idiomaElegido = teclado.nextLine();
        var idioma = Lenguaje.fromEspanol(idiomaElegido);
        List<Libro> librosEncontradosPorIdioma = libroRepositorio.findByLenguaje(idioma);
        System.out.println("Los libros encontrados son: ");
        librosEncontradosPorIdioma.forEach(System.out::println);
    }
}

