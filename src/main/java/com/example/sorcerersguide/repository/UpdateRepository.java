package com.example.sorcerersguide.repository;

import com.example.sorcerersguide.model.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UpdateRepository extends JpaRepository<Update, String> {

    Page<Update> findByHeadingContainingIgnoreCaseOrBodyContainingIgnoreCaseOrderByIdDesc(String heading, String body, Pageable pageable);

}
