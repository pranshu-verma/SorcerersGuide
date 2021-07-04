package com.example.sorcerersguide.service;

import com.example.sorcerersguide.model.Faq;
import com.example.sorcerersguide.model.Query;
import com.example.sorcerersguide.repository.FaqRepository;
import com.example.sorcerersguide.repository.QueriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    public Page<Faq> getSearchedFaqs(String question, String answer, Pageable pageable) {
        return faqRepository.findByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCaseOrderByIdDesc(question, answer, pageable);
    }

    public void deleteAllFaqs() {
        faqRepository.deleteAll();
    }

}
