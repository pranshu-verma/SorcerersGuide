package com.example.sorcerersguide.service;

import com.example.sorcerersguide.model.Update;
import com.example.sorcerersguide.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UpdateService {

    @Autowired
    private UpdateRepository updateRepository;

    public List<Update> getAllUpdates() {
        return updateRepository.findAll();
    }

    public Page<Update> getSearchedUpdates(String heading, String body, Pageable pageable) {
        return updateRepository.findByHeadingContainingIgnoreCaseOrBodyContainingIgnoreCaseOrderByIdDesc(heading, body, pageable);
    }

    public void deleteAllUpdates() {
        updateRepository.deleteAll();
    }

}
