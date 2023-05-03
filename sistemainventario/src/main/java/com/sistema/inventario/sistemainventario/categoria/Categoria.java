package com.sistema.inventario.sistemainventario.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sistema.inventario.sistemainventario.marca.Marca;

@Entity
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(Integer id) {
        this.id = id;
    }
    
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(String nombre, Marca marca) {
        this.nombre = nombre;
        this.marca = marca;
    }

    public Categoria(Integer id, String nombre, Marca marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
    }

    public Categoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
