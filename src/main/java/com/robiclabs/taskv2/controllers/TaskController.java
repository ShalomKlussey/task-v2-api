package com.robiclabs.taskv2.controllers;

import com.robiclabs.taskv2.models.Task;
import com.robiclabs.taskv2.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Operation(summary = "Get a task by ID", description = "Get a task based on their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found", content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        return taskService.findById(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all tasks", description = "Get a list of all tasks")
    @ApiResponse(responseCode = "200", description = "List of tasks", content = @Content(schema = @Schema(implementation = List.class)))
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {

        return new ResponseEntity<>(
                taskService.findAll(),
                HttpStatus.OK);
    }
    @Operation(summary = "Create a new task", description = "Create a new task")
    @ApiResponse(responseCode = "201", description = "Task created", content = @Content(schema = @Schema(implementation = Task.class)))
    @PostMapping
    public ResponseEntity<Task> createUser(@RequestBody Task task) {

        return new ResponseEntity<>(
                taskService.save(task),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Update a task", description = "Update an existing task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated", content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@Parameter(description = "ID of the task") @PathVariable Long id, @RequestBody Task updated) {
        Optional<Task> taskFound = taskService.findById(id);

        if (taskFound.isPresent()) {
            updated.setId(id);

            return new ResponseEntity<>(taskService.save(updated), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a task", description = "Delete a task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        Optional<Task> taskFound = taskService.findById(id);

        if (taskFound.isPresent()) {
            taskService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
