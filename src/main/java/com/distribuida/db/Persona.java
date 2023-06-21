package com.distribuida.db;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "person")
@SequenceGenerator(name="personaSeq", sequenceName = "persona_seq", allocationSize = 1)
public class Persona extends PanacheEntity {

//    @Id

    private Long id;

    @Column
    private String name;

    public Persona(){}

    public Persona(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
