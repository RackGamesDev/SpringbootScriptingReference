package com.ejemplo.SpringbootScriptingReference.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.SpringbootScriptingReference.DTOs.CrearUserDTO;
import com.ejemplo.SpringbootScriptingReference.Models.User;
import com.ejemplo.SpringbootScriptingReference.Services.UserService;




@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService svc;
    public UserController(UserService svc) {
        this.svc = svc;
    }

    //Rutas referentes a la entidad de usuario

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(svc.listAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CrearUserDTO dto) {
        User creado = svc.create(dto.getNickname(), dto.getEmail());
        return ResponseEntity.status(201).body(creado);
    }
    
    
}
