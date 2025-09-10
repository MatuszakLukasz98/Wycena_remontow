package com.lukasz.matuszak.wycena_remontow.dto;

import com.lukasz.matuszak.wycena_remontow.model.Estimate;

import java.math.BigDecimal;

public record EstimateDto (
        int id,
        String name,
        BigDecimal price,
        boolean accepted,
        int projectId,
        int contractorId,
        int assignmentId
) {
    public EstimateDto(Estimate e) {
        this(
                e.getId(),
                e.getName(),
                e.getPrice(),
                e.isAccepted(),
                e.getProject() == null ? null : e.getProject().getId(),
                e.getContractor() == null ? null : e.getContractor().getId(),
                e.getAssignment() == null ? null : e.getAssignment().getId()
        );
    }
}
