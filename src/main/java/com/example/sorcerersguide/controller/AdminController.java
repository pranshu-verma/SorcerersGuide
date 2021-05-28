package com.example.sorcerersguide.controller;

import com.example.sorcerersguide.excel.ExcelHelper;
import com.example.sorcerersguide.excel.ExcelService;
import com.example.sorcerersguide.excel.ResponseMessage;
import com.example.sorcerersguide.model.Update;
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
    ExcelService excelService;

    private final String prefixFolder = "/admin/";

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam MultipartFile file, @RequestParam String table) {
        String message;

        if (ExcelHelper.hasCSVFormat(file)) {
            try {
                excelService.save(file, table);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/updates/download/")
                        .path(Objects.requireNonNull(file.getOriginalFilename()))
                        .toUriString();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
            } catch (Exception e) {
                message = "Error: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(excelService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/updates")
    public ModelAndView updates() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(prefixFolder + "updates");
        return modelAndView;
    }

    @GetMapping("/queries")
    public ModelAndView queries() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(prefixFolder + "queries");
        return modelAndView;
    }

    @GetMapping("/faqs")
    public ModelAndView faqs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(prefixFolder + "faqs");
        return modelAndView;
    }

}
