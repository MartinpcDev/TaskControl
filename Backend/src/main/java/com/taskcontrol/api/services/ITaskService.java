package com.taskcontrol.api.services;

import com.taskcontrol.api.dto.request.TaskRequest;
import com.taskcontrol.api.dto.request.TaskUpdateRequest;
import com.taskcontrol.api.dto.response.AppResponse;
import com.taskcontrol.api.dto.response.DeleteResponse;
import com.taskcontrol.api.dto.response.TaskResponse;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;

public interface ITaskService {

  AppResponse allTasks(Pageable pageable);

  AppResponse allTasksByStatus(TaskStatus status, Pageable pageable);

  AppResponse allTasksByCategory(TaskCategory category, Pageable pageable);

  AppResponse allTasksByCategoryAndStatus(TaskCategory category, TaskStatus status,
      Pageable pageable);

  AppResponse tasksByDate(LocalDate date, Pageable pageable);

  AppResponse taskByDateAfter(LocalDate date, Pageable pageable);

  AppResponse taskByDateBefore(LocalDate date, Pageable pageable);

  AppResponse taskByDateBetween(LocalDate afterDate, LocalDate beforeDate,
      Pageable pageable);

  TaskResponse taskById(Long id);

  TaskResponse saveTask(TaskRequest request);

  TaskResponse updateTask(Long id, TaskUpdateRequest request);

  DeleteResponse deleteTask(Long id);
}
