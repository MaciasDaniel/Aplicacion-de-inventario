package com.sistema.inventario.sistemainventario;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.sistemainventario.carrito_compras.ArticuloCarrito;
import com.sistema.inventario.sistemainventario.carrito_compras.ArticuloCarritoRepository;
import com.sistema.inventario.sistemainventario.producto.Producto;
import com.sistema.inventario.sistemainventario.usuario.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTests {
    
    @Autowired
    private ArticuloCarritoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAgregarArticulo() {
        Producto producto = entityManager.find(Producto.class, 2);
        Usuario usuario = entityManager.find(Usuario.class, 6);

        ArticuloCarrito articulo = new ArticuloCarrito(1, producto, usuario);
        repository.save(articulo);
    }

    @Test
    public void testAgregarMultiplesArticulos() {
        Usuario usuario = new Usuario(6);
        Producto producto1 = new Producto(1);
        Producto producto2 = new Producto(2);

        ArticuloCarrito articulo1 = new ArticuloCarrito(5, producto1, usuario);
        ArticuloCarrito articulo2 = new ArticuloCarrito(3, producto2, usuario);
        
        repository.saveAll(List.of(articulo1, articulo2));
    }

    @Test
    public void testListarArticulos() {
        List<ArticuloCarrito> articulos = repository.findAll();
        articulos.forEach(System.out::println);
    }

    @Test
    public void testActualizarArticulos() {
        ArticuloCarrito articulo = repository.findById(1).get();
        articulo.setCantidad(11);
        articulo.setProducto(new Producto(2));
    }

    @Test
    public void testEliminarArticulo() {
        repository.deleteById(1);
    }
}
