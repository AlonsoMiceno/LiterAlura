package com.alejobeliz.proyectos.literatura.service;

import com.alejobeliz.proyectos.literatura.model.Autor;
import com.alejobeliz.proyectos.literatura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutorService {

    public AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorService(){};

    public Optional<Autor> obterAutorPorNome(String nombre){
         return autorRepository.obterAutorPorNome(nombre);
    }

    public void gravarAutor(Autor autor){
        autorRepository.save(autor);
    }

    public List<Autor> obterTodosOsAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> obterTodosOsAutoresVivosEmAno(int anio){
        return autorRepository.obterAutorVivoEmAnos(anio);
    }

}
