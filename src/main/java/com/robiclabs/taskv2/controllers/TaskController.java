package com.robiclabs.taskv2.controllers;

import com.robiclabs.taskv2.models.Task;
import com.robiclabs.taskv2.services.TaskService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        return taskService.findById(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {

        return new ResponseEntity<>(
                taskService.findAll(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createUser(@RequestBody Task task) {

        return new ResponseEntity<>(
                taskService.save(task),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateUser(@PathVariable Long id, @RequestBody Task updated) {
        Optional<Task> taskFound = taskService.findById(id);

        if (taskFound.isPresent()) {
            updated.setId(id);

            return new ResponseEntity<>(taskService.save(updated), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
