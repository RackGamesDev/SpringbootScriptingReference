package com.ejemplo.SpringbootScriptingReference.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ejemplo.SpringbootScriptingReference.Models.User;
import com.ejemplo.SpringbootScriptingReference.Models.UserSpecs;
import com.ejemplo.SpringbootScriptingReference.Repositories.UserRepository;

@Service
public class UserService { //Servicio para las operaciones sobre una
    @Autowired private UserRepository userRepo;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    //Operaciones varias cubriendo varios casos sobre la base de datos:

    @Transactional(readOnly=true) //Quiere decir que la operacion no tendra efecto hasta que termine exitosamente (db transaction)
    public List<User> listAll() {
        return userRepo.findByPublicoTrue().stream().peek(user -> user.setContrasegna(null)).collect(Collectors.toList());
    }

    @Transactional
    public User create(String nickname, String email, String contrasegna, Boolean publico, Optional<Integer> edad, Optional<List<String>> nombres) {
        User u = new User();
        u.setNickname(nickname);
        u.setEmail(email);
        u.setPublico(publico);
        u.setEdad(edad.orElse(null));
        u.setNombres(nombres.orElse(null));
        u.setContrasegna(passwordEncoder.encode(contrasegna));
        return userRepo.save(u);
    }

    @Transactional(readOnly=true)
    public Optional<User> getOne(Long id) {
        Optional<User> usuario = userRepo.findById(id);
        /*if (usuario.isPresent()) {
            usuario.get().setContrasegna(null);
        }*/
        return usuario;
        /*return userRepo.findById(id).map(user -> Map.of(
        "id",    user.getId(),
        "nickname",  user.getNickname(),
        "email", user.getEmail(),
        "edad", user.getEdad(),
        "nombres", user.getNombres()
        ));*/
    }

    @Transactional(readOnly=true)
    public Optional<User> getOne(String email) {
        return userRepo.findByEmail(email);
    }

    @Transactional(readOnly=true)
    public List<String> consultaCompleja(String email, Integer edad) {
        return userRepo.findAll(
        Specification.where(UserSpecs.emailIs(email))
                     .and(UserSpecs.ageEquals(edad)),
        Sort.by(Sort.Direction.ASC, "nickname"))
                    .stream()
                    .map(User::getNickname)
                    .collect(Collectors.toList());
    }

    @Transactional
    public Optional<User> update(Long id,Optional<String> nickname,Optional<String> email,Optional<String> contrasegna,Optional<Boolean> publico,Optional<Integer> edad,Optional<List<String>> nombres) {
        return userRepo.findById(id).map(user -> {
            nickname.ifPresent(user::setNickname);
            email.ifPresent(user::setEmail);
            contrasegna.ifPresent(c -> user.setContrasegna(passwordEncoder.encode(c)));
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

    @Transactional(readOnly = true)
    public Optional<String> login(String email, String contrasegna) {
        return userRepo.findByEmail(email)
            .filter(user -> passwordEncoder.matches(contrasegna, user.getContrasegna()))
            .map(user -> jwtService.generateToken(user.getId(), user.getEmail()));
    }
}
