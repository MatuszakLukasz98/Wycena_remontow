package com.lukasz.matuszak.wycena_remontow.logic;

import com.lukasz.matuszak.wycena_remontow.dto.CustomerWorkAssignmentDto;
import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import com.lukasz.matuszak.wycena_remontow.model.Project;
import com.lukasz.matuszak.wycena_remontow.model.TypeOfWork;
import com.lukasz.matuszak.wycena_remontow.repository.CustomerWorkAssignmentRepository;
import com.lukasz.matuszak.wycena_remontow.repository.ProjectRepository;
import com.lukasz.matuszak.wycena_remontow.repository.TypeOfWorkRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerWorksAssignmentService {
    private CustomerWorkAssignmentRepository customerWorkAssignmentRepository;
    private ProjectRepository projectRepository;
    private TypeOfWorkRepository typeOfWorkRepository;

    public CustomerWorksAssignmentService(CustomerWorkAssignmentRepository customerWorkAssignmentRepository, ProjectRepository projectRepository, TypeOfWorkRepository typeOfWorkRepository) {
        this.customerWorkAssignmentRepository = customerWorkAssignmentRepository;
        this.projectRepository = projectRepository;
        this.typeOfWorkRepository = typeOfWorkRepository;

    }


    public CustomerWorkAssignmentDto createCustomerWorkAssignment(int projectId, int typeOfWorkId, CustomerWorkAssignment customerWorkAssignment) {
        Project project =  projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project with id: " + projectId + " not found"));

        TypeOfWork typeOfWork = typeOfWorkRepository.findById(typeOfWorkId).orElseThrow(() -> new IllegalArgumentException("TypewOfWork not found"));

        customerWorkAssignment.setProject(project);
        customerWorkAssignment.setTypeOfWork(typeOfWork);
        CustomerWorkAssignment saved = customerWorkAssignmentRepository.save(customerWorkAssignment);
        return new CustomerWorkAssignmentDto(
                saved.getId(),
                saved.getArea(),
                saved.isActive(),
                typeOfWork.getId(),
                project.getId()
        );
    }


}
