package com.robiclabs.taskv2.repositories;

import com.robiclabs.taskv2.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
