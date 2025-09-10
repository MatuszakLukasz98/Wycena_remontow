package com.lukasz.matuszak.wycena_remontow.logic;


import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import com.lukasz.matuszak.wycena_remontow.model.Project;
import com.lukasz.matuszak.wycena_remontow.repository.CustomerRepository;
import com.lukasz.matuszak.wycena_remontow.repository.CustomerWorkAssignmentRepository;
import com.lukasz.matuszak.wycena_remontow.repository.ProjectRepository;
import com.lukasz.matuszak.wycena_remontow.repository.TypeOfWorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private CustomerRepository customerRepository;
    private TypeOfWorkRepository typeOfWorkRepository;
    private CustomerWorkAssignmentRepository customerWorkAssignmentRepository;


    public ProjectService(ProjectRepository projectRepository, CustomerRepository customerRepository, TypeOfWorkRepository typeOfWorkRepository, CustomerWorkAssignmentRepository customerWorkAssignmentRepository) {
        this.projectRepository = projectRepository;
        this.customerRepository = customerRepository;
        this.typeOfWorkRepository = typeOfWorkRepository;
        this.customerWorkAssignmentRepository = customerWorkAssignmentRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(int id, Project project) {
        return projectRepository.findById(id).map(project1 -> {
            project1.setName(project.getName());
            project1.setDescription(project.getDescription());
            project1.setCustomer(project.getCustomer());
            project1.setContractor(project.getContractor());
            project1.setActive(project.isActive());
            return projectRepository.save(project1);
        }).orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public boolean canBeClose(int id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return project.getAssignments().stream().allMatch(assignment -> !assignment.isActive());
    }

    @Transactional
    public Project closeProject(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        project.setActive(false);
        return projectRepository.save(project);
    }



}
