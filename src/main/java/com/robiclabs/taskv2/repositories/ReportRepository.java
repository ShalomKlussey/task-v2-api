package com.robiclabs.taskv2.repositories;

import com.robiclabs.taskv2.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
