package com.ejemplo.SpringbootScriptingReference.Controllers;

import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.ejemplo.SpringbootScriptingReference.DTOs.CrearUserDTO;
import com.ejemplo.SpringbootScriptingReference.DTOs.LoginDTO;
import com.ejemplo.SpringbootScriptingReference.Models.User;
import com.ejemplo.SpringbootScriptingReference.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    /*private final UserService svc;
    @Autowired
    public UserController(UserService svc) {
        this.svc = svc;
    }*/
    @Autowired private UserService svc;

    public static Long getAuthenticatedUser(HttpServletRequest request) throws ResponseStatusException { //Manejador para el middleware
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autenticado");
        return userId;
    }

    //Rutas referentes a la entidad de usuario

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(HttpServletRequest request, @PathVariable Long id) {
        Optional<User> usuario = svc.getOne(id);
        if (usuario.isEmpty()) return ResponseEntity.notFound().build();
        User user = usuario.get();
        if (user.getPublico()) return ResponseEntity.ok(user);
        Long idSesion = (Long) request.getAttribute("userId");
        if (idSesion == null || !idSesion.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAll(@RequestParam(defaultValue = "0") int pagina) {
        return ResponseEntity.ok(svc.listAll(pagina));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CrearUserDTO dto) {
        User creado = svc.create(dto.getNickname(), dto.getEmail(), dto.getContrasegna(), dto.getPublico(), Optional.ofNullable(dto.getEdad()), Optional.ofNullable(dto.getNombres()));
        return ResponseEntity.status(201).body(creado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(HttpServletRequest request, @PathVariable Long id, @RequestBody CrearUserDTO dto) {
        Long idSesion = getAuthenticatedUser(request);
        if (!idSesion.equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar a otro usuario");
        }
        User actualizado = svc.update(id, Optional.ofNullable(dto.getNickname()), Optional.ofNullable(dto.getEmail()), Optional.ofNullable(dto.getContrasegna()), Optional.ofNullable(dto.getPublico()), Optional.ofNullable(dto.getEdad()), Optional.ofNullable(dto.getNombres())).orElse(null);
        return ResponseEntity.status(200).body(actualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(HttpServletRequest request, @PathVariable Long id) {
        Long idSesion = getAuthenticatedUser(request);
        if (!idSesion.equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes eliminar a otro usuario");
        }
        return ResponseEntity.ok().body(svc.delete(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO dto) {
        return svc.login(dto.getEmail(), dto.getContrasegna())
            .map(token -> ResponseEntity.ok(Map.of("token", token)))
            .orElse(ResponseEntity.status(401).build());
    }
    
}
