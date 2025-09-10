package com.lukasz.matuszak.wycena_remontow.controller;

import com.lukasz.matuszak.wycena_remontow.dto.EstimateDto;
import com.lukasz.matuszak.wycena_remontow.logic.EstimateService;
import com.lukasz.matuszak.wycena_remontow.model.Estimate;
import com.lukasz.matuszak.wycena_remontow.repository.EstimateRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/estimates")
public class EstimateController {
    private EstimateService estimateService;

    public EstimateController(EstimateService estimateService, EstimateRepository estimateRepository) {
        this.estimateService = estimateService;
    }

    @GetMapping
    public ResponseEntity<List<Estimate>>  getAllEstimates() {
        return ResponseEntity.ok(estimateService.getAllEstimates());
    }

    @PostMapping
    public ResponseEntity<EstimateDto> createEstimate(@Valid @RequestBody CreateEstimateRequest request) {
        EstimateDto created = estimateService.createEstimate(
                request.getAssignmentId(),
                request.getContractorId(),
                request.getPrice(),
                request.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    public static class CreateEstimateRequest {
        @NotNull
        private Integer assignmentId;
        @NotNull
        private Integer contractorId;
        @NotNull
        private BigDecimal price;
        @NotBlank
        private String name;

        public Integer getAssignmentId() {
            return assignmentId;
        }

        public void setAssignmentId(Integer assignmentId) {
            this.assignmentId = assignmentId;
        }

        public Integer getContractorId() {
            return contractorId;
        }

        public void setContractorId(Integer contractorId) {
            this.contractorId = contractorId;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


