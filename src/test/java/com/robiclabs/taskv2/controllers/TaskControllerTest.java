package com.robiclabs.taskv2.controllers;

import com.robiclabs.taskv2.models.Task;
import com.robiclabs.taskv2.repositories.TaskRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class TaskControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    TaskRepository taskRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port + "/api";
        taskRepository.deleteAll();

        Task task_1 = new Task(1L, "Report", "Description 1", Boolean.FALSE);
        Task task_2 = new Task(2L, "Maintenance", "Description 2 again", Boolean.FALSE);
        Task task_3 = new Task(3L, "Meeting", "Description 2", Boolean.FALSE);
        Task task_4 = new Task(4L, "Meeting with Client", "Description ", Boolean.TRUE);

        taskRepository.saveAll(List.of(task_1, task_2, task_3, task_4));
    }

    @Test
    void shouldFindAllTasks() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/tasks")
                .then()
                .statusCode(200)    // expecting HTTP 200 OK
                .contentType(ContentType.JSON) // expecting JSON response content
                .body(".", hasSize(4));

    }

    @Test
    public void shouldCreateTask() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"title\": \"Maintenance\", \"note\": \"Description\", \"done\": \"false\" }")
                .when()
                .post("/tasks")
                .then()
                .statusCode(201) // expecting HTTP 201 Created
                .contentType(ContentType.JSON); // expecting JSON response content

    }

}
