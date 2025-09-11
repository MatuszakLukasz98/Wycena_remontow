package com.lukasz.matuszak.wycena_remontow.dto;

import java.util.List;

public class CustomerWorkAssignmentDto {
    private int id;
    private double area;
    private boolean active;
    private int typeOfWorkId;
    private int projectId;
    private List<EstimateDto> estimates;

    // konstruktor
    public CustomerWorkAssignmentDto(int id, double area, boolean active, int typeOfWorkId, int projectId, List<EstimateDto> estimates) {
        this.id = id;
        this.area = area;
        this.active = active;
        this.typeOfWorkId = typeOfWorkId;
        this.projectId = projectId;
        this.estimates = estimates;
    }

    public int getId() { return id; }
    public double getArea() { return area; }
    public boolean isActive() { return active; }
    public int getTypeOfWorkId() { return typeOfWorkId; }
    public int getProjectId() { return projectId; }
    public List<EstimateDto> getEstimates() { return estimates; }
}

