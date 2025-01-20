package com.alejobeliz.proyectos.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorRecord(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer fichaNascimento,
        @JsonAlias("death_year") Integer fichaFalecimento
) {
}
