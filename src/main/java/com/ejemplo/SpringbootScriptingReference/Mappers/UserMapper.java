package com.ejemplo.SpringbootScriptingReference.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ejemplo.SpringbootScriptingReference.DTOs.CrearUserDto;
import com.ejemplo.SpringbootScriptingReference.Models.User;

@Mapper(componentModel="spring")
public interface UserMapper {
    @Mapping(target="contrasegna", ignore=true)
    CrearUserDto toDto(User user);
}
