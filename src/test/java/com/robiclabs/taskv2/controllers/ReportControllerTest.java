package com.robiclabs.taskv2.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robiclabs.taskv2.models.Report;
import com.robiclabs.taskv2.repositories.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @MockBean
    private ReportRepository reportRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveTutorial() throws Exception{

        Report report = new Report(1L, "Report Test", "The Text of Report Test",
                LocalDate.now(), LocalTime.now(), 2L, 5L);

        mockMvc.perform(post("/api/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnOneReport() throws Exception{
        long id = 1L;
        Report report = new Report(id, "Report Test", "The Text of Report Test",
                LocalDate.now(), LocalTime.now(), 2L, 5L);

        when(reportRepository.findById(id)).thenReturn(Optional.of(report));
        mockMvc.perform((get("/api/reports/{id}", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(report.getTitle()))
                .andExpect(jsonPath("$.note").value(report.getNote()))
                .andDo(print());

    }

    @Test
    void shouldReturnNotFoundReport() throws Exception{
        long id = 1L;

        when(reportRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/reports/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnListOfReports() throws Exception{
        List<Report> reports = new ArrayList<>(
                Arrays.asList(
                        new Report(1L, "Report Test", "The Text of Report Test",
                                LocalDate.now(), LocalTime.now(), 2L, 5L),
                        new Report(2L, "Report Test", "The Text of Report Test",
                                LocalDate.now(), LocalTime.now(), 2L, 5L),
                        new Report(3L, "Report Test", "The Text of Report Test",
                                LocalDate.now(), LocalTime.now(), 2L, 5L),
                        new Report(4L, "Report Test", "The Text of Report Test",
                                LocalDate.now(), LocalTime.now(), 2L, 5L),
                        new Report(5L, "Report Test", "The Text of Report Test",
                                LocalDate.now(), LocalTime.now(), 2L, 5L)
                )
        );

        when(reportRepository.findAll()).thenReturn(reports);

        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(reports.size()))
                .andDo(print());
    }

    @Test
    void shouldUpdateReport() throws Exception{

        long id = 1L;
        Report report = new Report(id, "Report Test", "The Text of Report Test",
                LocalDate.now(), LocalTime.now(), 2L, 5L);

        Report updatedReport = new Report(id, "Report Test updated", "The Text of Report Test updated",
                LocalDate.now(), LocalTime.now(), 2L, 5L);

        when(reportRepository.findById(id)).thenReturn(Optional.of(report));
        when(reportRepository.save(any(Report.class))).thenReturn(updatedReport);

        mockMvc.perform(put("/api/reports/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedReport)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedReport.getTitle()))
                .andExpect(jsonPath("$.note").value(updatedReport.getNote()))
                .andDo(print());
    }

    @Test
    void shouldDeleteReport() throws Exception{

        long id = 1L;
        doNothing().when(reportRepository).deleteById(id);

        mockMvc.perform(delete("/api/reports/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
   /* @Test
    void shouldDoSomething() throws Exception {
        // set expectation for TutorialRepository using Mockito
        when(tutorialRepository...).thenReturn(...);

        // perform HTTP request and set the expectations with MockMVC
        mockMvc.perform(...).andExpect(...).andExpect(...);
    }*/

}
