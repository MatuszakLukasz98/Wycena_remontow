package com.lukasz.matuszak.wycena_remontow.logic;

import com.lukasz.matuszak.wycena_remontow.dto.EstimateDto;
import com.lukasz.matuszak.wycena_remontow.model.Contractor;
import com.lukasz.matuszak.wycena_remontow.model.CustomerWorkAssignment;
import com.lukasz.matuszak.wycena_remontow.model.Estimate;
import com.lukasz.matuszak.wycena_remontow.repository.ContractorRepository;
import com.lukasz.matuszak.wycena_remontow.repository.CustomerWorkAssignmentRepository;
import com.lukasz.matuszak.wycena_remontow.repository.EstimateRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EstimateService {
    private EstimateRepository estimateRepository;
    private ContractorRepository contractorRepository;
    private CustomerWorkAssignmentRepository customerWorkAssignmentRepository;

    public EstimateService(EstimateRepository estimateRepository, ContractorRepository contractorRepository, CustomerWorkAssignmentRepository customerWorkAssignmentRepository) {
        this.estimateRepository = estimateRepository;
        this.contractorRepository = contractorRepository;
        this.customerWorkAssignmentRepository = customerWorkAssignmentRepository;
    }

    public EstimateDto createEstimate(int assignmentId, int contractorId, BigDecimal price, String name) {
        CustomerWorkAssignment customerWorkAssignment = customerWorkAssignmentRepository
                .findById(assignmentId).orElseThrow(() -> new IllegalArgumentException("CustomerWorkAssignment not found"));

        Contractor contractor = contractorRepository.findById(contractorId).orElseThrow(() -> new IllegalArgumentException("Contractor not found"));

        Estimate estimate = new Estimate();
        estimate.setName(name);
        estimate.setPrice(price);
        estimate.setContractor(contractor);
        estimate.setAssignment(customerWorkAssignment);
        estimate.setProject(customerWorkAssignment.getProject());

        return new EstimateDto(estimateRepository.save(estimate));
    }

    @Transactional
    public Estimate acceptEstimate(int estimateId) {
        Estimate estimate = estimateRepository.findById(estimateId).orElseThrow(() -> new IllegalArgumentException("Estimate not found"));
        estimate.setAccepted(true);
        return estimateRepository.save(estimate);
    }

    public List<Estimate> getAllEstimates() {
        return estimateRepository.findAll();
    }
    public List<Estimate> getEstimatesByCustomerWorksAssignmentId(int customerWorksAssignmentId) {
        return estimateRepository.getEstimatesByAssignmentId(customerWorksAssignmentId);
    }
}
