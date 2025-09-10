package com.lukasz.matuszak.wycena_remontow.dto;

import com.lukasz.matuszak.wycena_remontow.model.Contractor;
import com.lukasz.matuszak.wycena_remontow.model.Customer;
import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class ProjectDto {
    private int id;
    private String name;
    private String description;
    private Customer customer;
    private boolean active = true;
    private Contractor contractor;
    private List<CustomerWorkAssignment> assignments = new ArrayList<>();

}
