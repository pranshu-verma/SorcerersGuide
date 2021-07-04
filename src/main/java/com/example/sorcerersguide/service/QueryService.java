package com.example.sorcerersguide.service;

import com.example.sorcerersguide.model.Query;
import com.example.sorcerersguide.repository.QueriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QueryService {

    @Autowired
    private QueriesRepository queriesRepository;

    public List<Query> getAllQueries() {
        return queriesRepository.findAll();
    }

    public Page<Query> getSearchedQueries(String heading, String body, Pageable pageable) {
        return queriesRepository.findByQueryQuestionContainingIgnoreCaseOrResolverResponseContainingIgnoreCaseOrderByCreatedDateDesc(heading, body, pageable);
    }

    public void deleteAllQueries() {
        queriesRepository.deleteAll();
    }

}
