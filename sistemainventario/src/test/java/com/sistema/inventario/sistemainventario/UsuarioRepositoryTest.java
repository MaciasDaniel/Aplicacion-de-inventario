package com.sistema.inventario.sistemainventario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.sistemainventario.usuario.Rol;
import com.sistema.inventario.sistemainventario.usuario.Usuario;
import com.sistema.inventario.sistemainventario.usuario.UsuarioRepository;
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCrearRoles() {
        Rol rolAdmin = new Rol("Administrador");
        Rol rolEditor = new Rol("Editor");
        Rol rolVisitante = new Rol("Visitante");

        entityManager.persist(rolAdmin);
        entityManager.persist(rolEditor);
        entityManager.persist(rolVisitante);
    }

    @Test
    public void testCrearNuevoUsuarioConUnRol() {
        Rol rolAdmin = entityManager.find(Rol.class, 1);
        Usuario usuario = new Usuario("dany@gmail.com", "12345");

        usuario.agregarRol(rolAdmin);
        usuarioRepository.save(usuario);
    }

    @Test
    public void crearNuevoUsuarioConDosRoles() {
        Rol rolEditor = entityManager.find(Rol.class, 2);
        Rol rolVisitante = entityManager.find(Rol.class, 3);

        Usuario usuario = new Usuario("luis@gmail.com", "1234551");

        usuario.agregarRol(rolEditor);
        usuario.agregarRol(rolVisitante);
        usuarioRepository.save(usuario);
    }

    @Test
    public void testAsignarRolAusuarioExistente() {
        Usuario usuario = usuarioRepository.findById(1).get();
        Rol rolEditor = entityManager.find(Rol.class, 2);

        usuario.agregarRol(rolEditor);
        usuarioRepository.save(usuario);
    }

    @Test
    public void testEliminarRolDeUnUsuarioExistente() {
        Usuario usuario = usuarioRepository.findById(1).get();
        Rol rol = new Rol(2);
        usuario.eliminarRol(rol);
    }

    @Test
    public void testCrearNuevoUsuarioConNuevoRol() {
        Rol rolVendedor = new Rol("Vendedor");
        Usuario usuario = new Usuario("ariana@gmail.com", "1234741");

        usuario.agregarRol(rolVendedor);
        usuarioRepository.save(usuario);
    }

    @Test
    public void testObtenerUsuario() {
        Usuario usuario = usuarioRepository.findById(1).get();
        entityManager.detach(usuario);

        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRoles());
    }

    @Test
    public void testEliminarUsuario() {
        usuarioRepository.deleteById(2);
    }
}
