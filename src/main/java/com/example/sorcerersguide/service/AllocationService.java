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

    public List<Allocation> findByReviewerIdAndDate(String reviewerId, String date) {
        return allocationRepository.findByReviewerIdAndDate(reviewerId, date);
    }

    public List<Allocation> findByReviewerIdAndDateAndIsCompleted(String reviewerId, String date, String isCompleted) {
        return allocationRepository.findByReviewerIdAndDateAndIsCompleted(reviewerId, date, isCompleted);
    }

    public Allocation findByCaseId(String id) {
        Optional<Allocation> allocation = allocationRepository.findById(id);
        return allocation.orElse(null);
    }

    public void saveAllocation(Allocation allocation) {
        allocationRepository.save(allocation);
    }

}
