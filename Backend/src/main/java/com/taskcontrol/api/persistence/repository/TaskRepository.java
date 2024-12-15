package com.taskcontrol.api.persistence.repository;

import com.taskcontrol.api.persistence.model.Task;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  Page<Task> findAllByStatus(TaskStatus status, Pageable pageable);

  Page<Task> findAllByCategory(TaskCategory category, Pageable pageable);

  Page<Task> findAllByCategoryAndStatus(TaskCategory category, TaskStatus status,
      Pageable pageable);

  Page<Task> findAllByDate(LocalDate date, Pageable pageable);

  Page<Task> findAllByDateAfter(LocalDate date, Pageable pageable);

  Page<Task> findAllByDateBefore(LocalDate date, Pageable pageable);

  Page<Task> findAllByDateBetween(LocalDate dateAfter, LocalDate dateBefore, Pageable pageable);
}
