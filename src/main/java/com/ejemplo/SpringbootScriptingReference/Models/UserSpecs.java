package com.ejemplo.SpringbootScriptingReference.Models;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecs { //Usado para las consultas complejas
    public static Specification<User> emailIs(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<User> ageEquals(Integer age) {
        return (root, query, cb) -> cb.equal(root.get("age"), age);
    }
}
