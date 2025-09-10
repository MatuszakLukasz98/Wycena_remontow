package com.lukasz.matuszak.wycena_remontow.repository;

import com.lukasz.matuszak.wycena_remontow.model.Contractor;
import com.lukasz.matuszak.wycena_remontow.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor,Integer> {
}
