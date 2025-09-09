package com.lukasz.matuszak.wycena_remontow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity
@Table(name = "Contractors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String nip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Project> projects;


}
