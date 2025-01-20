package com.alejobeliz.proyectos.literatura.service;

import com.alejobeliz.proyectos.literatura.api.PeticaoAPI;
import com.alejobeliz.proyectos.literatura.model.Autor;
import com.alejobeliz.proyectos.literatura.model.Idioma;
import com.alejobeliz.proyectos.literatura.model.Livro;
import com.alejobeliz.proyectos.literatura.model.livroRecord;
import com.alejobeliz.proyectos.literatura.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {
    private PeticaoAPI peticaoAPI;
    private Scanner sc;
    private LivroService livroService;
    private AutorService autorService;
    private JsonParser jsonParser;

    @Autowired
    public MenuService(LivroService livroService, AutorService autorService, JsonParser jsonParser) {
        this.peticaoAPI = new PeticaoAPI();
        this.sc = new Scanner(System.in);
        this.livroService = livroService;
        this.autorService = autorService;
        this.jsonParser = jsonParser;
    }

    public void salvarLivro() {
        List<livroRecord> librosObtenidos = obtenerLibrosApi();

        if (librosObtenidos.isEmpty()) {
            System.out.println("No se encontró ningun libro");
            return;
        }

        System.out.println("Escoja un libro para guardar[0-Cancelar]");
        for (int i = 0; i < librosObtenidos.size(); i++) {
            System.out.println((i + 1) + " - " + librosObtenidos.get(i).titulo() + " - " + librosObtenidos.get(i).idiomas().get(0) + " - " + librosObtenidos.get(i).autores().get(0).nome());
        }

        int opcion = sc.nextInt();
        sc.nextLine();
        if (opcion == 0) {
            return;
        }
        if (opcion < 1 || opcion > librosObtenidos.size()) {
            System.out.println("Error: número erroneo");
            return;
        }

        livroRecord livroRecord = librosObtenidos.get(opcion - 1);
        Optional<Livro> libroTraidoDelRepo = livroService.obterLivrosPorNome(livroRecord.titulo());
        Optional<Autor> autorTraidodelRepo = autorService.obterAutorPorNome(livroRecord.autores().get(0).nome());

        if (libroTraidoDelRepo.isPresent()) {
            System.out.println("Error: no se puede registrar dos veces el mismo libro");
            return;
        }

        Livro livro = new Livro(livroRecord);

        if (!autorTraidodelRepo.isPresent()) {
            Autor autorNuevo = livro.getAutor();
            autorService.gravarAutor(autorNuevo);
        }

        livroService.gravarLivro(livro);
    }

    public List<livroRecord> obtenerLibrosApi() {
        System.out.print("Ingrese el título del libro [0-Cancelar]: ");
        String titulo = sc.nextLine();
        if (titulo.equals("0")) {
            return Collections.emptyList();
        }
        List<livroRecord> librosObtenidos;
        librosObtenidos = jsonParser.parsearLibros(peticaoAPI.obtenerDatos(titulo));
        return librosObtenidos;
    }


    public void listarLivrosRegistrados() {
        List<Livro> livros = livroService.obterTodosOsLivros();
        livros.forEach(livro -> livro.imprimirInformacao());
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorService.obterTodosOsAutores();
        autores.forEach(autor -> autor.imprimirInformacion());
    }

    public void listarAutoresVivosEmAnos() {
        try {
            System.out.print("Ingrese año: ");
            int anio = sc.nextInt();
            sc.nextLine();
            List<Autor> autores = autorService.obterTodosOsAutoresVivosEmAno(anio);
            autores.forEach(autor -> autor.imprimirInformacion());
        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número");
        }

    }

    public void listarLivrosPorIdioma() {
        Idioma.listarIdiomas();
        System.out.print("Ingrese el codigo del idioma [0-Cancelar]: ");
        String idiomaBuscado = sc.nextLine();
        if (idiomaBuscado.equals("0")) {
            return;
        }
        List<Livro> livros = livroService.obterLivrosPorIdioma(Idioma.stringToEnum(idiomaBuscado));
        livros.forEach(livro -> livro.imprimirInformacao());
    }

}
