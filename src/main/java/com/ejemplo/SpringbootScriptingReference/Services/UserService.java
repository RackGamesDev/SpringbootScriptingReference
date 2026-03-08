package com.ejemplo.SpringbootScriptingReference.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.SpringbootScriptingReference.Models.User;
import com.ejemplo.SpringbootScriptingReference.Repositories.UserRepository;

@Service
public class UserService { //Servicio para las operaciones sobre una
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(readOnly=true) //Quiere decir qeu la operacion no tendra efecto hasta que termine exitosamente (db transaction)
    public List<User> listAll() {
        return userRepo.findAll();
    }

    @Transactional
    public User create(String nickname, String email, Boolean publico, Optional<Integer> edad, Optional<List<String>> nombres) {
        User u = new User();
        u.setNickname(nickname);
        u.setEmail(email);
        u.setPublico(publico);
        u.setEdad(edad.orElse(0));
        u.setNombres(nombres.orElse(null));
        return userRepo.save(u);
    }
}
