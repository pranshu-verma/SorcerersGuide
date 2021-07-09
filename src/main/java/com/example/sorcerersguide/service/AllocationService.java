package com.example.sorcerersguide.service;

import com.example.sorcerersguide.model.Allocation;
import com.example.sorcerersguide.repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Page<Allocation> findByReviewerId(String reviewerId, Pageable pageable) {
        return allocationRepository.findByReviewerId(reviewerId, pageable);
    }

}
