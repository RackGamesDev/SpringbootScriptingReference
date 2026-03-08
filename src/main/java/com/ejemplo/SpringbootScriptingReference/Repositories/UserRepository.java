package com.ejemplo.SpringbootScriptingReference.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ejemplo.SpringbootScriptingReference.Models.User;

public interface UserRepository extends JpaRepository<User, Object>, JpaSpecificationExecutor<User>{ //Extiende las funcionalidades de base de datos que un modelo/entidad tiene
    //Los nombres son estrictos para que pueda reconocer que se busca y como. <find/read/query/count/get><nombre de propiedad>By<nombre propiedad> por ejemplo
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    //List<String> findNicknameAndEdadByEmailAndNickname(String email, String nickname);

}
