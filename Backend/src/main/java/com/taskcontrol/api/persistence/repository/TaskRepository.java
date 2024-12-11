package com.taskcontrol.api.persistence.repository;

import com.taskcontrol.api.persistence.model.Task;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findAllByTaskStatus(TaskStatus status);

  List<Task> findAllByTaskCategory(TaskCategory category);

  List<Task> findAllByTaskDate(LocalDate date);

  List<Task> findAllByTaskDateAfter(LocalDate date);

  List<Task> findAllByTaskDateBefore(LocalDate date);

  List<Task> findAllByTaskDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
