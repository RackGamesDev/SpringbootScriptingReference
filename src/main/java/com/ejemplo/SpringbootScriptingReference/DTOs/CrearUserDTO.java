package com.ejemplo.SpringbootScriptingReference.DTOs;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CrearUserDTO {
    
    @Null
    private Long id;

    @NotBlank(message="No puede estar en blanco")
    @Size(max=200, message="Maximo 200")
    @NotNull
    @Getter
    private String nickname;

    @Email
    @NotNull
    @Getter
    private String email;

    @NotNull
    @Getter
    private Boolean publico;

    @Getter
    private Integer edad;

    @Getter
    private List<String> nombres;

    @Getter
    @NotNull
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$\n",message="Contrasegna segura")
    private String contrasegna;

}
