package com.lukasz.matuszak.wycena_remontow.repository;

import com.lukasz.matuszak.wycena_remontow.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    List<Project> findByContractor_Id(int contractorId);
    List<Project> findByActiveIsTrue();




}
