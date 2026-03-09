package com.ejemplo.SpringbootScriptingReference.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.SpringbootScriptingReference.Models.Comentario;
import com.ejemplo.SpringbootScriptingReference.Repositories.ComentarioRepository;
import com.ejemplo.SpringbootScriptingReference.Repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioService {
    @Autowired private UserRepository userRepo;
    @Autowired private ComentarioRepository comentarioRepo;

    @Transactional(readOnly=true)
    public Optional<Comentario> getOne(Long id) {
        return comentarioRepo.findById(id);
    }

    @Transactional
    public Optional<Comentario> crear(String contenido, Long idUsuario) {
        
    }


}
