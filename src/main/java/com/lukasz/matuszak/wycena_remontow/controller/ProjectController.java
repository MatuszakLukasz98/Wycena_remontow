package com.lukasz.matuszak.wycena_remontow.controller;

import com.lukasz.matuszak.wycena_remontow.logic.ProjectService;
import com.lukasz.matuszak.wycena_remontow.model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RequestMapping("/projects")
@RestController
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.created(URI.create("/projects")).body(createdProject);
    }

    @PutMapping("/{id}")
    ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project project) {
        try {
            return ResponseEntity.ok(projectService.updateProject(id, project));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/close")
    ResponseEntity<Project> closeProject(@PathVariable Integer id) {
        if (!projectService.canBeClose(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(projectService.closeProject(id));
    }
}
