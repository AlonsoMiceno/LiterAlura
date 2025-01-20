package com.alejobeliz.proyectos.literatura.service;

import com.alejobeliz.proyectos.literatura.model.Idioma;
import com.alejobeliz.proyectos.literatura.model.Livro;
import com.alejobeliz.proyectos.literatura.repository.livroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LivroService {

    private livroRepositorio repositorioLivro;

    @Autowired
    public LivroService(livroRepositorio repository) {
        this.repositorioLivro = repository;
    }

    public LivroService(){};

    public List<Livro> obterTodosOsLivros() {
        return repositorioLivro.findAll();
    }

    public Optional<Livro> obterLivrosPorNome(String nombre){
        return repositorioLivro.obtenerLibroPorNombre(nombre);
    }

    public List<Livro> obterLivrosPorIdioma(Idioma idioma){
        return repositorioLivro.obtenerLibrosPorIdioma(idioma);
    }

    public void gravarLivro(Livro livro){
        repositorioLivro.save(livro);
    }
}
