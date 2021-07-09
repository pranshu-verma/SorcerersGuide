package com.example.sorcerersguide.excel;

import com.example.sorcerersguide.model.Allocation;
import com.example.sorcerersguide.model.Faq;
import com.example.sorcerersguide.model.Query;
import com.example.sorcerersguide.model.Update;
import com.example.sorcerersguide.repository.AllocationRepository;
import com.example.sorcerersguide.repository.FaqRepository;
import com.example.sorcerersguide.repository.QueriesRepository;
import com.example.sorcerersguide.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    UpdateRepository updateRepository;

    @Autowired
    QueriesRepository queriesRepository;

    @Autowired
    FaqRepository faqRepository;

    @Autowired
    AllocationRepository allocationRepository;

    public void save(MultipartFile file, String table) {
        try {
            switch (table) {
                case "updates":
                    List<Update> updates = ExcelHelper.csvToUpdates(file.getInputStream());
                    updateRepository.saveAll(updates);
                    break;
                case "queries":
                    List<Query> queries = ExcelHelper.csvToQueries(file.getInputStream());
                    queriesRepository.saveAll(queries);
                    break;
                case "faqs":
                    List<Faq> faqs = ExcelHelper.csvToFaqs(file.getInputStream());
                    faqRepository.saveAll(faqs);
                    break;
                case "allocations":
                    List<Allocation> allocations = ExcelHelper.csvToAllocations(file.getInputStream());
                    allocationRepository.saveAll(allocations);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store CSV data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(String tableName) {

        switch (tableName) {
            case "updates":
                List<Update> updates = updateRepository.findAll();
                return ExcelHelper.updatesToCsv(updates);
            case "queries":
                List<Query> queries = queriesRepository.findAll();
                return ExcelHelper.queriesToCsv(queries);
            case "faqs":
                List<Faq> faqs = faqRepository.findAll();
                return ExcelHelper.faqsToCsv(faqs);
            case "allocations":
                List<Allocation> allocations = allocationRepository.findAll();
                return ExcelHelper.allocationsToCsv(allocations);
        }

        return new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray());
    }

}
