package com.devguard.loganalyzer.controller;

import com.devguard.loganalyzer.model.LogAnalysis;
import com.devguard.loganalyzer.repository.LogRepository;
import com.devguard.loganalyzer.service.LogAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*") // Allows connection from React frontend
public class LogController {

    @Autowired
    private LogAnalysisService analysisService;

    @Autowired
    private LogRepository logRepository;

    @PostMapping("/analyze")
    public LogAnalysis analyzeLog(@RequestBody String rawLog) {
        return analysisService.analyzeAndSaveLog(rawLog);
    }

    @GetMapping("/history")
    public List<LogAnalysis> getLogHistory() {
        return logRepository.findAll();
    }
}
