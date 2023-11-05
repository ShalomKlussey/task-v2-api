package com.robiclabs.taskv2.services;

import com.robiclabs.taskv2.models.Report;
import com.robiclabs.taskv2.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;



}
