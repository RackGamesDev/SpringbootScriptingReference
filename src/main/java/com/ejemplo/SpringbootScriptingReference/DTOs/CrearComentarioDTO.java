package com.ejemplo.SpringbootScriptingReference.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearComentarioDTO {
    @NotNull
    private String contenido;
}
