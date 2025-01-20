package com.alejobeliz.proyectos.literatura.ui;

public class Menu {
    private String menu = """
    1-Buscar livro por título
    2-Listar livros registrados
    3-Listar autores registrados
    4-Listar autores vivos em um determinado ano
    5-Listar livros por idioma
    0-Salir
    
    Escolha uma opcao: """;
    private String bemVindos = "Bem-vindos a literAlura";

    public String getMenu() {
        return menu;
    }

    public String getBemVindos() {
        return bemVindos;
    }
}
