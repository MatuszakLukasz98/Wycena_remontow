package com.lukasz.matuszak.wycena_remontow.repository;

import com.lukasz.matuszak.wycena_remontow.model.TypeOfWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeOfWorkRepository extends JpaRepository<TypeOfWork, Long> {

    Optional<TypeOfWork> findById(int id);
    List<TypeOfWork> findByNameContaining(String keyword);
}
