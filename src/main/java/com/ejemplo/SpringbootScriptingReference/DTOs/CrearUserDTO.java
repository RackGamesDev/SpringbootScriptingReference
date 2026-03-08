package com.ejemplo.SpringbootScriptingReference.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CrearUserDTO {

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

}
