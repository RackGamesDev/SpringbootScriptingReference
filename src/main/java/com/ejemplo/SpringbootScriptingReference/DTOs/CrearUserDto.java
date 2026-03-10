package com.ejemplo.SpringbootScriptingReference.DTOs;

import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CrearUserDto {
    
    //@Null
    //private Long id;

    @NotBlank(message="No puede estar en blanco")
    @Size(max=200, message="Maximo 200")
    @NotNull(message="Debe de incluir el nickname")
    //@UniqueElements(message="El nickname ya esta en uso")
    @Getter
    private String nickname;

    @Email(message="El email debe de ser valido")
    @NotNull(message="Debe incluir el email")
    @Getter
    private String email;

    @NotNull(message="Debe incluir si es publico o no")
    @Getter
    private Boolean publico;

    @Getter
    @Positive(message="Debe ser positivo")
    @Max(value = 150, message = "Debe de ser menos de 150")
    private Integer edad;

    @Getter
    @NonBlankList(message="Debe de ser un array de strings")
    private List<String> nombres;

    @Getter
    @NotNull(message="Debe incluir la contrasegna")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",message="Contrasegna segura")
    private String contrasegna;

}
