package com.lukasz.matuszak.wycena_remontow.dto;

import com.lukasz.matuszak.wycena_remontow.model.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class CustomerDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private Set<Project> projects;
}
