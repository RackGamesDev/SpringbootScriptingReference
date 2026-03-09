package com.ejemplo.SpringbootScriptingReference.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginDTO {
    @NotNull
    @Email
    private String email;
    
    @NotNull
    private String contrasegna;
}
