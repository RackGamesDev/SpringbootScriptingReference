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
    public User create(String nickname, String email, String contrasegna, Boolean publico, Optional<Integer> edad, Optional<List<String>> nombres) {
        User u = new User();
        u.setNickname(nickname);
        u.setEmail(email);
        u.setPublico(publico);
        u.setEdad(edad.orElse(0));
        u.setNombres(nombres.orElse(null));
        u.setContrasegna(contrasegna);
        return userRepo.save(u);
    }

    @Transactional(readOnly=true)
    public Optional<User> getOne(Long id) {
        return userRepo.findById(id);
    }

    @Transactional(readOnly=true)
    public Optional<User> getOne(String email) {
        return userRepo.findByEmail(email);
    }

    @Transactional
    public Optional<User> update(Long id,
                                 Optional<String> nickname,
                                 Optional<String> email,
                                 Optional<String> contrasegna,
                                 Optional<Boolean> publico,
                                 Optional<Integer> edad,
                                 Optional<List<String>> nombres) {

        return userRepo.findById(id).map(user -> {
            nickname.ifPresent(user::setNickname);
            email.ifPresent(user::setEmail);
            contrasegna.ifPresent(user::setContrasegna);
            publico.ifPresent(user::setPublico);
            edad.ifPresentOrElse(user::setEdad, () -> user.setEdad(null));
            nombres.ifPresentOrElse(user::setNombres, () -> user.setNombres(null));

            return userRepo.save(user);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!userRepo.existsById(id)) {
            return false;
        }
        userRepo.deleteById(id);
        return true;
    }
}
