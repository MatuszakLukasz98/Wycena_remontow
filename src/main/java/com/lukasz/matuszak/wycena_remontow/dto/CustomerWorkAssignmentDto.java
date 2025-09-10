package com.lukasz.matuszak.wycena_remontow.dto;

import com.lukasz.matuszak.wycena_remontow.model.Estimate;

import java.util.List;

public record CustomerWorkAssignmentDto(
        int id,
        double area,
        boolean active,
        int typeOfWorkId,
        int projectId,
        List<EstimateDto> estimates
) {
}
