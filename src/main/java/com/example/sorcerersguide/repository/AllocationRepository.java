package com.example.sorcerersguide.repository;

import com.example.sorcerersguide.model.Allocation;
import com.example.sorcerersguide.model.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllocationRepository extends JpaRepository<Allocation, String> {

    List<Allocation> findByReviewerIdAndDate(String reviewerId, String date);

    List<Allocation> findByReviewerIdAndDateAndIsCompleted(String reviewerId, String date, String isCompleted);

}
