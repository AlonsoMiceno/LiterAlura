package com.alejobeliz.proyectos.literatura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(AutorRecord autor){
        this.nombre=autor.nome();
        this.fechaNacimiento= autor.fichaNascimento();
        this.fechaFallecimiento= autor.fechaFallecimiento();
    }

    public Autor(){};

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Livro> getLibros() {
        return livros;
    }

    public void setLibros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nome='" + nombre + '\'' +
                ", fichaNascimento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento +
                ", libros=" + livros +
                '}';
    }

    public void imprimirInformacion() {
        System.out.println("*****Autor*****");
        System.out.println("Nomber:" + nombre);
        System.out.println("Fecha de nacimiento: "+fechaNacimiento);
        System.out.println("Fecha de fallecimiento: "+fechaFallecimiento);
        System.out.println("Libros: "+ livros);
        System.out.println("");
    }
}
