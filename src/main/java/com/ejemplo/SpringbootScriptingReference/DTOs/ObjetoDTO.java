package com.ejemplo.SpringbootScriptingReference.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Clase que define normas, util para validar peticiones en un controller
//Tambien define datos entre capas, se podria usar como objeto
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ObjetoDTO {

    private Long id;

    //Ejemplos de validaciones:

    @NotBlank(message="No puede estar en blanco")
    @Size(max=200, message="Maximo 200")
    @NotNull
    private String titulo;

    @Email
    @NotNull
    private String email;

    @Min(value=0,message="Debe ser positivo")
    @Max(value=256,message="Maximo 256")
    @NotNull
    private Integer numero;

    @Pattern(regexp="^(https?://).+",message="Url valida")
    @NotNull
    private String url;

    @PastOrPresent(message="Fecha pasada")
    private java.time.LocalDateTime fecha; //Opcional

    @Positive(message="Debe ser positivo")
    private Double precio;
}
