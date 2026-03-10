package com.ejemplo.SpringbootScriptingReference.Mappers;

import org.mapstruct.Mapper;

import com.ejemplo.SpringbootScriptingReference.DTOs.CrearUserDto;

@Mapper(componentModel="spring")
public interface UserMapper {
    CrearUserDto toDto(User user);
}
