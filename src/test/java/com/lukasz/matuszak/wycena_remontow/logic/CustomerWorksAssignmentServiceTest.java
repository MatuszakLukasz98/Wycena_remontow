package com.lukasz.matuszak.wycena_remontow.logic;

import com.lukasz.matuszak.wycena_remontow.dto.CustomerWorkAssignmentDto;
import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import com.lukasz.matuszak.wycena_remontow.model.Project;
import com.lukasz.matuszak.wycena_remontow.model.TypeOfWork;
import com.lukasz.matuszak.wycena_remontow.repository.CustomerWorkAssignmentRepository;
import com.lukasz.matuszak.wycena_remontow.repository.ProjectRepository;
import com.lukasz.matuszak.wycena_remontow.repository.TypeOfWorkRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerWorksAssignmentServiceTest {

    @Test
    void createCustomerWorkAssignmentShouldReturnCustomerWorkAssignmentDto() {
        //given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        TypeOfWorkRepository typeOfWorkRepository = mock(TypeOfWorkRepository.class);
        CustomerWorkAssignmentRepository customerWorkAssignmentRepository = mock(CustomerWorkAssignmentRepository.class);
        EstimateService estimateService = mock(EstimateService.class);
        CustomerWorksAssignmentService customerWorksAssignmentService = new CustomerWorksAssignmentService(customerWorkAssignmentRepository, projectRepository, typeOfWorkRepository, estimateService);

        int projectId = 1;
        int typeOfWorkId = 2;

        Project project = new Project();
        project.setId(projectId);

        TypeOfWork typeOfWork = new TypeOfWork();
        typeOfWork.setId(typeOfWorkId);

        CustomerWorkAssignment assignment = new CustomerWorkAssignment();
        assignment.setArea(50.d);
        assignment.setActive(true);

        CustomerWorkAssignment savedAssignment = new  CustomerWorkAssignment();
        savedAssignment.setId(100);
        savedAssignment.setArea(50d);
        savedAssignment.setActive(true);
        savedAssignment.setProject(project);
        savedAssignment.setTypeOfWork(typeOfWork);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(typeOfWorkRepository.findById(typeOfWorkId)).thenReturn(Optional.of(typeOfWork));
        when(customerWorkAssignmentRepository.save(any(CustomerWorkAssignment.class))).thenReturn(savedAssignment);

        //when
        CustomerWorkAssignmentDto result =
                customerWorksAssignmentService.createCustomerWorkAssignment(projectId, typeOfWorkId, assignment);


        //then
        assertThat(result.getId()).isEqualTo(100);
        assertThat(result.getArea()).isEqualTo(50);
        assertThat(result.isActive()).isTrue();
        assertThat(result.getProjectId()).isEqualTo(projectId);
        assertThat(result.getTypeOfWorkId()).isEqualTo(typeOfWorkId);
        assertThat(result.getEstimates()).isEmpty();
    }
}