package com.ejemplo.SpringbootScriptingReference.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.SpringbootScriptingReference.Models.User;
import com.ejemplo.SpringbootScriptingReference.Repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService { //Servicio para las operaciones sobre una
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(readOnly=true)
    public List<User> listAll() {
        return userRepo.findAll();
    }

    @Transactional
    public User create(String nickname, String email) {
        User u = new User();
        u.setNickname(nickname);
        u.setEmail(email);
        return userRepo.save(u);
    }
}
