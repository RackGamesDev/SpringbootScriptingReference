package com.ejemplo.SpringbootScriptingReference.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ejemplo.SpringbootScriptingReference.Models.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Object>, JpaSpecificationExecutor<Comentario>{
    List<Comentario> findByUserId(Long userId);
}