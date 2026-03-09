package com.ejemplo.SpringbootScriptingReference.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ejemplo.SpringbootScriptingReference.DTOs.CrearComentarioDTO;
import com.ejemplo.SpringbootScriptingReference.Models.Comentario;
import com.ejemplo.SpringbootScriptingReference.Services.ComentarioService;
import com.ejemplo.SpringbootScriptingReference.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
public class ComentarioController {
    @Autowired private ComentarioService svcComentario;
    @Autowired private UserService svcUser;
    //@Autowired private UserController userController;

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(svcComentario.getOne(id).orElse(null));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Comentario>> getFromUser(@PathVariable Long id) {
        return ResponseEntity.ok(svcComentario.verDeUsuario(id));
    }

    @PostMapping
    public ResponseEntity<Comentario> crear(HttpServletRequest request, @RequestBody CrearComentarioDTO dto) {
        Long idSesion = UserController.getAuthenticatedUser(request);
        Comentario comentario = svcComentario.crear(dto.getContenido(), idSesion);
        return ResponseEntity.ok().body(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> borrar(HttpServletRequest request, @PathVariable Long id) {
        Long idSesion = UserController.getAuthenticatedUser(request);
        Optional<Comentario> comentario = svcComentario.getOne(id);
        if (!comentario.isPresent() || !idSesion.equals(comentario.get().getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El comentario no existe o no tienes permisos");
        }
        return ResponseEntity.ok().body(svcComentario.borrar(id));
    }

}
