package com.alejobeliz.proyectos.literatura.repository;

import com.alejobeliz.proyectos.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE LOWER(a.nome) LIKE LOWER(:nome)")
    Optional<Autor> obterAutorPorNome(String nome);

    @Query("SELECT a FROM Autor a WHERE :ano>=a.fichaNascimento AND :ano<a.fechaFallecimiento")
    List<Autor> obterAutorVivoEmAnos(int ano);
}
