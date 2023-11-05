package com.robiclabs.taskv2.repositories;

import com.robiclabs.taskv2.models.Report;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void shouldFindNoReport(){

        Iterable<Report> reports = reportRepository.findAll();

        assertThat(reports).isEmpty();
    }

    @Test
    public void shouldStoreReport(){
        Report saved = reportRepository.save(Report.builder()
                        .title("Report Test")
                        .note("the note of Test")
                        .assignedTo(5L)
                        .createdBy(2L)
                        .createdOn(LocalDate.now())
                        .createdAt(LocalTime.now())
                        .build());

        assertThat(saved).hasFieldOrPropertyWithValue("title", "Report Test");
        assertThat(saved).hasFieldOrPropertyWithValue("note", "the note of Test");
        assertThat(saved).hasFieldOrPropertyWithValue("createdBy", 2L);
        assertThat(saved).hasFieldOrPropertyWithValue("assignedTo", 5L);
    }

    @Test
    public void shouldFindAllReports(){
        Report report1 = Report.builder()
                .title("Report Test_1")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report1);

        Report report2 = Report.builder()
                .title("Report Test_2")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report2);

        Report report3 = Report.builder()
                .title("Report Test_3")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report3);

        Iterable<Report> reports = reportRepository.findAll();

        assertThat(reports).hasSize(3).contains(report1, report2, report3);
    }

    @Test
    public void shouldFindReportById(){
        Report report1 = Report.builder()
                .title("Report Test_1")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report1);

        Report report2 = Report.builder()
                .title("Report Test_2")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report2);

        Report found = reportRepository.findById(report2.getId()).get();

        assertThat(found).isEqualTo(report2);


    }

    @Test
    public void shouldDeleteById(){
        Report report1 = Report.builder()
                .title("Report Test_1")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report1);

        Report report2 = Report.builder()
                .title("Report Test_2")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report2);

        Report report3 = Report.builder()
                .title("Report Test_3")
                .note("the note of Test")
                .assignedTo(5L)
                .createdBy(2L)
                .createdOn(LocalDate.now())
                .createdAt(LocalTime.now())
                .build();

        testEntityManager.persist(report3);

        reportRepository.deleteById(report2.getId());
        Iterable<Report> reports = reportRepository.findAll();

        assertThat(reports).hasSize(2).contains(report1, report3);
    }

}
