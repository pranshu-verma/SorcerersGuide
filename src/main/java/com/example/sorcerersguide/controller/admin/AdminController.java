package com.example.sorcerersguide.controller.admin;

import com.example.sorcerersguide.excel.ExcelHelper;
import com.example.sorcerersguide.excel.ExcelService;
import com.example.sorcerersguide.excel.ResponseMessage;
import com.example.sorcerersguide.model.Faq;
import com.example.sorcerersguide.model.Query;
import com.example.sorcerersguide.model.Update;
import com.example.sorcerersguide.service.AllocationService;
import com.example.sorcerersguide.service.FaqService;
import com.example.sorcerersguide.service.QueryService;
import com.example.sorcerersguide.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private FaqService faqService;

    @Autowired
    private AllocationService allocationService;

    @PostMapping("/{activeProgram}/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable String activeProgram, @RequestParam MultipartFile file, @RequestParam String table) {
        String message;

        if (ExcelHelper.hasCSVFormat(file)) {
            try {
                excelService.save(file, table);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/admin/download/" + table + "/")
                        .path(Objects.requireNonNull(file.getOriginalFilename()))
                        .toUriString();
                System.out.println(fileDownloadUri);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
            } catch (Exception e) {
                message = "Error: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteAllUpdates(@RequestParam String table) {
        String message;
        try {
            switch (table) {
            case "updates":
                updateService.deleteAllUpdates();
                break;
            case "queries":
                queryService.deleteAllQueries();
                break;
            case "faqs":
                faqService.deleteAllFaqs();
                break;
            case "allocations":
                allocationService.deleteAllAllocations();
                break;
        }
            message = "Deleted successfully!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, ""));
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
        }
    }

    @GetMapping("/download/{tableName}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String tableName, @PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(excelService.load(tableName));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }


}
