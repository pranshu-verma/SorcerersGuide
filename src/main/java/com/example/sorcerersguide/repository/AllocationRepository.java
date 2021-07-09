package com.example.sorcerersguide.repository;

import com.example.sorcerersguide.model.Allocation;
import com.example.sorcerersguide.model.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    Page<Allocation> findByReviewerId(String reviewerId, Pageable pageable);

}
