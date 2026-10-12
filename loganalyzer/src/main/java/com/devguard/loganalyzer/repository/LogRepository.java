package com.devguard.loganalyzer.repository;

import com.devguard.loganalyzer.model.LogAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogAnalysis, Long> {
}