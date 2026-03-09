package com.ejemplo.SpringbootScriptingReference.Models;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="COMENTARIOS")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable=false)
    @Getter
    @Setter
    private String texto;

    //Relacion 1:N en la base de datos, pero en Java se vera como un campo de tipo User
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnoreProperties({"comentarios", "contrasegna", "hibernateLazyInitializer"})
    //@JsonIgnore
    private User user;

    @Column(nullable=false, updatable=false)
    @CreationTimestamp
    private LocalDateTime creacion;
    
}
