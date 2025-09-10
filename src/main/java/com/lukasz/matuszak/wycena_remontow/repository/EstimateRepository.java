package com.lukasz.matuszak.wycena_remontow.repository;

import com.lukasz.matuszak.wycena_remontow.model.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimate, Integer> {

    List<Estimate> getEstimatesByAssignmentId(int customerWorksAssignmentId);
}
