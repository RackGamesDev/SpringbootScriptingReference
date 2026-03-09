package com.ejemplo.SpringbootScriptingReference.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ejemplo.SpringbootScriptingReference.Models.Comentario;
import com.ejemplo.SpringbootScriptingReference.Models.User;
import com.ejemplo.SpringbootScriptingReference.Repositories.ComentarioRepository;
import com.ejemplo.SpringbootScriptingReference.Repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ComentarioService {
    @Autowired private UserRepository userRepo;
    @Autowired private ComentarioRepository comentarioRepo;

    @Transactional(readOnly=true)
    public Optional<Comentario> getOne(Long id) {
        return comentarioRepo.findById(id);
    }

    @Transactional
    public Comentario crear(String contenido, Long idUsuario) {
        User usuario = userRepo.findById(idUsuario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        Comentario comentario = new Comentario();
        comentario.setTexto(contenido);
        comentario.setUser(usuario);
        return comentarioRepo.save(comentario);
    }

    @Transactional
    public boolean borrar(Long id) {
        if (!comentarioRepo.existsById(id)) {
            return false;
        }
        comentarioRepo.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<Comentario> verDeUsuario(Long id) {
        return comentarioRepo.findByUserId(id);
    }


}
