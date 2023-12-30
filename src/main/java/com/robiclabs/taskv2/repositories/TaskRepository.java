package com.robiclabs.taskv2.repositories;

import com.robiclabs.taskv2.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitle(String title);
}
