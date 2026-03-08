package com.ejemplo.SpringbootScriptingReference.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.SpringbootScriptingReference.Models.User;

public interface UserRepository extends JpaRepository<User, Object>{ //Extiende las funcionalidades de base de datos que un modelo/entidad tiene
    Optional<User> findByNickname(String nickname);
}
