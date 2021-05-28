package com.example.sorcerersguide.repository;

import com.example.sorcerersguide.model.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueriesRepository extends JpaRepository<Query, Long> {

    Page<Query> findByQueryQuestionContainingIgnoreCaseOrResolverResponseContainingIgnoreCaseOrderByCreatedDateDesc(String heading, String body, Pageable pageable);

}
