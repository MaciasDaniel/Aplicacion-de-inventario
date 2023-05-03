package com.sistema.inventario.sistemainventario.producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sistema.inventario.sistemainventario.categoria.Categoria;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String nombre;

    private float precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoDetails> detalles = new ArrayList<>();

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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void agregarDetalles(String nombre, String valor) {
        this.detalles.add(new ProductoDetails(nombre, valor, this));
    }

    public List<ProductoDetails> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ProductoDetails> detalles) {
        this.detalles = detalles;
    }

    public void setDetalle(Integer id, String nombre, String valor) {
        this.detalles.add(new ProductoDetails(id, nombre, valor, this));
    }

    public Producto(Integer id, String nombre, float precio, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto() {
    }

    public Producto(String nombre, float precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + "]";
    }
}
