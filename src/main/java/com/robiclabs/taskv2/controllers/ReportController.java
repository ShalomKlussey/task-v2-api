package com.robiclabs.taskv2.controllers;

import com.robiclabs.taskv2.models.Report;
import com.robiclabs.taskv2.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllReports(){

        try{
            List<Report> reports = reportRepository.findAll();

            if(reports.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") Long id){

        Optional<Report> report = reportRepository.findById(id);

        return report.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/reports")
    public ResponseEntity<Report> saveReport(@RequestBody Report report){

        try{
            Report newReport = Report.builder().title(report.getTitle())
                    .note(report.getNote())
                    .assignedTo(report.getAssignedTo())
                    .createdBy(report.getCreatedBy())
                    .createdAt(LocalTime.now())
                    .createdOn(LocalDate.now())
                    .build();

            Report reportSaved = reportRepository.save(newReport);

            return new ResponseEntity<>(reportSaved, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/report")
    public ResponseEntity<HttpStatus> deleteReport(@PathVariable("id") Long id){
        try{
            reportRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
