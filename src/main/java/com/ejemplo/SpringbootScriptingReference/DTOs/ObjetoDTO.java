package com.ejemplo.SpringbootScriptingReference.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Clase que define normas, util para validar peticiones en un controller
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ObjetoDTO {
    @NotBlank(message="No puede estar en blanco")
    @Size(max=200, message="Maximo 200")
    private String titulo;
}
