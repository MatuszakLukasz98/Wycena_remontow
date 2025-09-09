package com.lukasz.matuszak.wycena_remontow.repository;

import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerWorkAssignmentRepository extends JpaRepository<CustomerWorkAssignment, Integer> {
}
