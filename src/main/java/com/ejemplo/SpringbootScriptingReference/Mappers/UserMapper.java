package com.ejemplo.SpringbootScriptingReference.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ejemplo.SpringbootScriptingReference.DTOs.CrearUserDto;
import com.ejemplo.SpringbootScriptingReference.Models.User;

//Un mapper se usa para transformar un objeto en otro sin tener que poner todos los campos
@Mapper(componentModel="spring")
public interface UserMapper {
    @Mapping(target="contrasegna", ignore=true)
    //@Mapping(target="fecha", expression="java(java.time.LocalDateTime.now())" source="fechas.creacion")
    CrearUserDto toDto(User user);
}
