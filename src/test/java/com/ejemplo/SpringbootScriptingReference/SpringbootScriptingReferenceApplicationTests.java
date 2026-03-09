package com.ejemplo.SpringbootScriptingReference;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplo.SpringbootScriptingReference.Models.Comentario;
import com.ejemplo.SpringbootScriptingReference.Services.ComentarioService;
import com.ejemplo.SpringbootScriptingReference.Services.UserService;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import com.ejemplo.SpringbootScriptingReference.Models.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringbootScriptingReferenceApplicationTests {

	@Autowired private UserService userService;
    @Autowired private ComentarioService comentarioService;

    // shared between tests
    private static Long userId;
    private static Long comentarioId;

    @Test
    @Order(1)
    void crearUsuario() {
        User user = userService.create(
            "testNickname",
            "test@email.com",
            "Password1!",
            true,
            Optional.of(25),
            Optional.empty()
        );

        assertNotNull(user.getId());
        assertEquals("testNickname", user.getNickname());
        userId = user.getId(); // save for next tests
    }

    @Test
    @Order(2)
    void crearComentario() {
        Comentario comentario = comentarioService.crear("Hola mundo", userId);

        assertNotNull(comentario.getId());
        assertEquals("Hola mundo", comentario.getTexto());
        assertEquals(userId, comentario.getUser().getId());
        comentarioId = comentario.getId();
    }

    @Test
    @Order(3)
    void leerUsuarioYComentario() {
        Optional<User> user = userService.getOne(userId);
        Optional<Comentario> comentario = comentarioService.getOne(comentarioId);

        assertTrue(user.isPresent());
        assertTrue(comentario.isPresent());
        assertEquals("testNickname", user.get().getNickname());
        assertEquals("Hola mundo", comentario.get().getTexto());
    }

    @Test
    @Order(4)
    void eliminarTodo() {
        boolean comentarioBorrado = comentarioService.borrar(comentarioId);
        boolean usuarioBorrado = userService.delete(userId);

        assertTrue(comentarioBorrado);
        assertTrue(usuarioBorrado);

        assertTrue(userService.getOne(userId).isEmpty());
        assertTrue(comentarioService.getOne(comentarioId).isEmpty());
    }

}
