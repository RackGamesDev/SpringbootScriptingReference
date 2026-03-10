package com.ejemplo.SpringbootScriptingReference.Controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.SpringbootScriptingReference.DTOs.ObjetoDto;
import com.ejemplo.SpringbootScriptingReference.Services.EjemploService;

import jakarta.validation.Valid;


@RestController //La diferencia con el @Controller normal es que este devuelve json a modo de api rest y el controller asecas devuelve vistas
@RequestMapping("/api") //Los endpoints empezarian por /api/
public class ApiController {

    private final EjemploService ejemploService;
    public ApiController(EjemploService ejemploService) {
        this.ejemploService = ejemploService;
    }

    @GetMapping(value = "/prueba",produces = MediaType.APPLICATION_JSON_VALUE) //Se define la ruta y el tipo de objeto que devolvera, en este caso un json, adems en el metodo get
    public Map<String, Object> prueba() {
        int resultado = ejemploService.ejemplo(4); //Uso de otros servicios
        return Map.of(
                "message", "funciona",
                "valor", resultado,
                "status", 200
        );
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE) //Peticion post, define la ruta, lo que entra (texto del body) y lo que devuelve (json)
    public Map<String, Object> crear(@RequestBody Map<String, Object> body) {
        System.out.println(body.toString()); //Body en formato json
        
        return Map.of(
                "message", "funciona",
                "status", 200
        );
    }

    @DeleteMapping("/borrar/{id}")
    public Map<String, Object> borrar(@RequestHeader(value="X-header-ejemplo",required=false) String header, @PathVariable String id) {

        return Map.of(
                "message", "funciona",
                "status", 200,
                "parametro", id, //Parametro en la URL
                "header", header //Header de la peticion
        );
    }

    @PostMapping("/validado")
    public ResponseEntity<ObjetoDto> validar(@Valid @RequestBody ObjetoDto body) { //Obliga a que el body cumpla un DTO y devuelve un objeto de ese mismo tipo
        body.setId(4l);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
    
}
