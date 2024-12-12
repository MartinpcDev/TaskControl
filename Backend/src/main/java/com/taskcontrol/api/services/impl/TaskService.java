package com.taskcontrol.api.services.impl;

import com.taskcontrol.api.dto.request.TaskRequest;
import com.taskcontrol.api.dto.request.TaskUpdateRequest;
import com.taskcontrol.api.dto.response.DeleteResponse;
import com.taskcontrol.api.dto.response.TaskResponse;
import com.taskcontrol.api.exceptions.TaskNotFoundException;
import com.taskcontrol.api.mapper.TaskMapper;
import com.taskcontrol.api.persistence.model.Task;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import com.taskcontrol.api.persistence.repository.TaskRepository;
import com.taskcontrol.api.services.ITaskService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<TaskResponse> allTasks() {
    return TaskMapper.toTaskListResponse(taskRepository.findAll());
  }

  @Override
  public List<TaskResponse> allTasksByStatus(TaskStatus status) {
    return TaskMapper.toTaskListResponse(taskRepository.findAllByStatus(status));
  }

  @Override
  public List<TaskResponse> allTasksByCategory(TaskCategory category) {
    return TaskMapper.toTaskListResponse(taskRepository.findAllByCategory(category));
  }

  @Override
  public List<TaskResponse> allTasksByCategoryAndStatus(TaskCategory category, TaskStatus status) {
    return TaskMapper.toTaskListResponse(
        taskRepository.findAllByCategoryAndStatus(category, status));
  }

  @Override
  public List<TaskResponse> tasksByDate(LocalDate date) {
    return TaskMapper.toTaskListResponse(taskRepository.findAllByDate(date));
  }

  @Override
  public List<TaskResponse> taskByDateAfter(LocalDate date) {
    return TaskMapper.toTaskListResponse(taskRepository.findAllByDateAfter(date));
  }

  @Override
  public List<TaskResponse> taskByDateBefore(LocalDate date) {
    return TaskMapper.toTaskListResponse(taskRepository.findAllByDateBefore(date));
  }

  @Override
  public List<TaskResponse> taskByDateBetween(LocalDate afterDate, LocalDate beforeDate) {
    return TaskMapper.toTaskListResponse(
        taskRepository.findAllByDateBetween(afterDate, beforeDate));
  }

  @Override
  public TaskResponse taskById(Long id) {
    return TaskMapper.toTaskResponse(this.extractTask(id));
  }

  @Override
  public TaskResponse saveTask(TaskRequest request) {
    Task tasktoSave = TaskMapper.toTaskEntity(request);
    Task savedTask = taskRepository.save(tasktoSave);
    return TaskMapper.toTaskResponse(savedTask);
  }

  @Override
  public TaskResponse updateTask(Long id, TaskUpdateRequest request) {
    Task task = this.extractTask(id);
    TaskMapper.updateTask(task, request);
    return TaskMapper.toTaskResponse(taskRepository.save(task));
  }

  @Override
  public DeleteResponse deleteTask(Long id) {
    Task task = this.extractTask(id);
    taskRepository.delete(task);
    return new DeleteResponse("Task Eliminado Correctamente");
  }

  private Task extractTask(Long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("No se encontro el task con el id: " + id));
  }
}
