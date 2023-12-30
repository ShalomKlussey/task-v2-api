package com.robiclabs.taskv2.services;

import com.robiclabs.taskv2.models.Task;
import com.robiclabs.taskv2.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task){
        return taskRepository.save(task);
    }
    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    public List<Task> findByTitle(String title){

        return taskRepository.findByTitle(title);
    }
    public List<Task> findAll(){
        return taskRepository.findAll();
    }
    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }
}
