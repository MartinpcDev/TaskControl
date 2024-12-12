package com.taskcontrol.api.services;

import com.taskcontrol.api.dto.request.TaskRequest;
import com.taskcontrol.api.dto.request.TaskUpdateRequest;
import com.taskcontrol.api.dto.response.DeleteResponse;
import com.taskcontrol.api.dto.response.TaskResponse;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;

public interface ITaskService {

  List<TaskResponse> allTasks();

  List<TaskResponse> allTasksByStatus(TaskStatus status);

  List<TaskResponse> allTasksByCategory(TaskCategory category);

  List<TaskResponse> allTasksByCategoryAndStatus(TaskCategory category, TaskStatus status);

  List<TaskResponse> tasksByDate(LocalDate date);

  List<TaskResponse> taskByDateAfter(LocalDate date);

  List<TaskResponse> taskByDateBefore(LocalDate date);

  List<TaskResponse> taskByDateBetween(LocalDate afterDate, LocalDate beforeDate);

  TaskResponse taskById(Long id);

  TaskResponse saveTask(TaskRequest request);

  TaskResponse updateTask(Long id, TaskUpdateRequest request);

  DeleteResponse deleteTask(Long id);
}
