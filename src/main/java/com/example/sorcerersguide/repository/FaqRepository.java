package com.example.sorcerersguide.repository;

import com.example.sorcerersguide.model.Faq;
import com.example.sorcerersguide.model.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    Page<Faq> findByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCaseOrderByIdDesc(String question, String answer, Pageable pageable);

}
