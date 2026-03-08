package com.ejemplo.SpringbootScriptingReference.Models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Entidad asociada a la base de datos (se debe hacer el .sql en resources/db/migration igualmente, a no ser que este configurado como en el application.yml (create/create-drop/updte/validate/none))
@Entity
@Table(name="USERS")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable=false, unique=true)
    @Getter
    @Setter
    private String nickname;

    @Column(nullable=false)
    @Getter
    @Setter
    private String email;

    //public User() {}

    /*public User(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;

    }*/

    /*public Long getId() {return id;}
    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}*/

    @PrePersist
    protected void onCreate() {
        /////
    }
    @PreUpdate
    protected void onUpdate() {
        /////
    }
    @PostPersist void afterCreate() {
        /////
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.email + " - " + this.nickname;
    }
}
