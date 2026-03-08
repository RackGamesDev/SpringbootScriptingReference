package com.ejemplo.SpringbootScriptingReference.Models;

import java.util.List;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Entidad asociada a la base de datos (se debe hacer el .sql en resources/db/migration igualmente, a no ser que este configurado como en el application.yml (create/create-drop/updte/validate/none))
@Entity
@Table(name="USERS")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable=false, unique=true)
    @Getter
    @Setter
    private String nickname;

    @Column(nullable=false, unique=true)
    @Getter
    @Setter
    private String email;

    @Column(nullable=true)
    @Getter
    @Setter
    private Integer edad;

    @Column(nullable=false)
    @Getter
    @Setter
    private Boolean publico;

    @Column(nullable=true)
    @Getter
    @Setter
    private List<String> nombres;

    @Column(nullable=false)
    @Getter
    @Setter
    private String contrasegna;

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
        //Codigo a ejecutar antes de la insercion sql
    }
    @PreUpdate
    protected void onUpdate() {
        //Codigo a ejecutar antes de el update sql
    }
    @PostPersist void afterCreate() {
        //Codigo a ejecutar despues de la insercion sql
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }*/

    @Override
    public String toString() {
        return this.email + " - " + this.nickname;
    }
}
