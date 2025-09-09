package com.lukasz.matuszak.wycena_remontow.controller;

import com.lukasz.matuszak.wycena_remontow.dto.CustomerWorkAssignmentDto;
import com.lukasz.matuszak.wycena_remontow.logic.CustomerWorksAssignmentService;
import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RequestMapping("/project/{id}/works")
@RestController
public class CustomerWorkAssignmentController {
    CustomerWorksAssignmentService customerWorksAssignmentService;

    public CustomerWorkAssignmentController(CustomerWorksAssignmentService customerWorksAssignmentService) {
        this.customerWorksAssignmentService = customerWorksAssignmentService;
    }

    @PostMapping
    public ResponseEntity<CustomerWorkAssignmentDto> createCustomerWorkAssignment(
            @PathVariable("id") int projectId,
            @RequestParam("typeOfWorkId") int typeOfWorkId,
            @RequestBody CustomerWorkAssignment assignment) {
        CustomerWorkAssignmentDto created = customerWorksAssignmentService
                .createCustomerWorkAssignment(projectId, typeOfWorkId, assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
