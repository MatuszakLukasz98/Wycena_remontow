package com.lukasz.matuszak.wycena_remontow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Customer's name must be not null")
    private String name;
    @NotBlank(message = "Customer's surname must be not null")
    private String surname;
    private String email;
    private String city;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Project> projects;

    public Customer(String name, String surname, String email, String city) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.projects = null;
    }
}
