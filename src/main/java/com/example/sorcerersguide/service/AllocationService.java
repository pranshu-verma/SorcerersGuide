package com.example.sorcerersguide.service;

import com.example.sorcerersguide.model.Allocation;
import com.example.sorcerersguide.repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepository;

    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    public void deleteAllAllocations() {
        allocationRepository.deleteAll();
    }

    public List<Allocation> findByReviewerId(String reviewerId) {
        return allocationRepository.findByReviewerId(reviewerId);
    }

    public Allocation findByCaseId(String id) {
        Optional<Allocation> allocation = allocationRepository.findById(id);
        return allocation.orElse(null);
    }

    public void saveAllocation(Allocation allocation) {
        allocationRepository.save(allocation);
    }

}
