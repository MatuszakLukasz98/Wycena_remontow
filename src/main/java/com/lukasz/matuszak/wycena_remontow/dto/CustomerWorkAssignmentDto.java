package com.lukasz.matuszak.wycena_remontow.dto;

public record CustomerWorkAssignmentDto(
        int id,
        double area,
        boolean active,
        int typeOfWorkId,
        int projectId
) {
}
