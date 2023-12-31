package com.robiclabs.taskv2;

import com.robiclabs.taskv2.models.Task;
import com.robiclabs.taskv2.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Taskv2Application {


	public static void main(String[] args) {
		SpringApplication.run(Taskv2Application.class, args);
	}

	@Autowired
	TaskRepository taskRepository;

	@Bean
	@ConditionalOnProperty(prefix = "app", name = "db.init", havingValue = "true")
	public CommandLineRunner demoCommandLineRunner() {
		return args -> {

			log.info("Running.....");

			Task task_1 = new Task(1L, "Report", "Description 1", Boolean.FALSE);
			Task task_2 = new Task(2L, "Maintenance", "Description 2 again", Boolean.FALSE);
			Task task_3 = new Task(3L, "Meeting", "Description 2", Boolean.FALSE);
			Task task_4 = new Task(4L, "Meeting with Client", "Description ", Boolean.TRUE);

			taskRepository.saveAll(List.of(task_1, task_2, task_3, task_4));

			log.info("Init done!");
		};
	}
}
