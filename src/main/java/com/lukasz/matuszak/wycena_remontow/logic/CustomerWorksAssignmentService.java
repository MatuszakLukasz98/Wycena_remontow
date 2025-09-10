package com.lukasz.matuszak.wycena_remontow.logic;

import com.lukasz.matuszak.wycena_remontow.dto.CustomerWorkAssignmentDto;
import com.lukasz.matuszak.wycena_remontow.dto.EstimateDto;
import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import com.lukasz.matuszak.wycena_remontow.model.Estimate;
import com.lukasz.matuszak.wycena_remontow.model.Project;
import com.lukasz.matuszak.wycena_remontow.model.TypeOfWork;
import com.lukasz.matuszak.wycena_remontow.repository.CustomerWorkAssignmentRepository;
import com.lukasz.matuszak.wycena_remontow.repository.EstimateRepository;
import com.lukasz.matuszak.wycena_remontow.repository.ProjectRepository;
import com.lukasz.matuszak.wycena_remontow.repository.TypeOfWorkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerWorksAssignmentService {
    private CustomerWorkAssignmentRepository customerWorkAssignmentRepository;
    private ProjectRepository projectRepository;
    private TypeOfWorkRepository typeOfWorkRepository;
    private EstimateService estimateService;

    public CustomerWorksAssignmentService(CustomerWorkAssignmentRepository customerWorkAssignmentRepository, ProjectRepository projectRepository
            , TypeOfWorkRepository typeOfWorkRepository,  EstimateService estimateService) {
        this.customerWorkAssignmentRepository = customerWorkAssignmentRepository;
        this.projectRepository = projectRepository;
        this.typeOfWorkRepository = typeOfWorkRepository;
        this.estimateService = estimateService;

    }


    public CustomerWorkAssignmentDto createCustomerWorkAssignment(int projectId, int typeOfWorkId, CustomerWorkAssignment customerWorkAssignment) {
        Project project =  projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project with id: " + projectId + " not found"));

        TypeOfWork typeOfWork = typeOfWorkRepository.findById(typeOfWorkId).orElseThrow(() -> new IllegalArgumentException("TypeOfWork not found"));

        customerWorkAssignment.setProject(project);
        customerWorkAssignment.setTypeOfWork(typeOfWork);
        CustomerWorkAssignment saved = customerWorkAssignmentRepository.save(customerWorkAssignment);

/*        Nie przemyślałem tego lista zawsze będzie pusta w momencie wywoływania tej metody, na razie zostawiam jakby przydała się w innej metodzie
        List<EstimateDto> estimates = estimateService
                .getEstimatesByCustomerWorksAssignmentId(saved.getId())
                .stream()
                .map(EstimateDto::new)
                .toList();*/
        List<EstimateDto> estimates = Collections.emptyList();

        return new CustomerWorkAssignmentDto(
                saved.getId(),
                saved.getArea(),
                saved.isActive(),
                typeOfWork.getId(),
                project.getId(),
                estimates

        );
    }


}
