package com.taskcontrol.api.persistence.repository;

import com.taskcontrol.api.persistence.model.Task;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findAllByStatus(TaskStatus status);

  List<Task> findAllByCategory(TaskCategory category);

  List<Task> findAllByCategoryAndStatus(TaskCategory category, TaskStatus status);

  List<Task> findAllByDate(LocalDate date);

  List<Task> findAllByDateAfter(LocalDate date);

  List<Task> findAllByDateBefore(LocalDate date);

  List<Task> findAllByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
