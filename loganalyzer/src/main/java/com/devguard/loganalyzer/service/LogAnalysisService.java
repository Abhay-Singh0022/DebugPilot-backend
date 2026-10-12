package com.devguard.loganalyzer.service;

import com.devguard.loganalyzer.model.LogAnalysis;
import com.devguard.loganalyzer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogAnalysisService {

    @Autowired
    private LogRepository logRepository;

    public LogAnalysis analyzeAndSaveLog(String rawLog) {
        LogAnalysis analysis = new LogAnalysis();
        analysis.setRawLog(rawLog);

        try {
            // Simulated AI Analysis Logic (Prompting & Output Handling)
            if (rawLog.contains("NullPointerException")) {
                analysis.setRootCause("Attempted to access or invoke a method on an object reference that is null.");
                analysis.setSeverity("High");
                analysis.setSuggestedFix("Perform null checks (e.g., `if (obj != null)`) or use Optional before accessing method calls.");
            } else if (rawLog.contains("SQLException") || rawLog.contains("Database")) {
                analysis.setRootCause("Database connection failure or bad SQL query execution.");
                analysis.setSeverity("Critical");
                analysis.setSuggestedFix("Check database URL credentials in application.properties and verify if DB server is running.");
            } else {
                analysis.setRootCause("Unclassified runtime exception in application execution.");
                analysis.setSeverity("Medium");
                analysis.setSuggestedFix("Inspect stack trace line numbers and verify method parameter contracts.");
            }
        } catch (Exception e) {
            // LLM Failure Mode Fallback Handling
            analysis.setRootCause("AI Service Processing Error: Unable to structure log safely.");
            analysis.setSeverity("Low");
            analysis.setSuggestedFix("Fallback to manual log inspection.");
        }

        return logRepository.save(analysis);
    }
}