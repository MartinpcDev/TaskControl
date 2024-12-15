package com.taskcontrol.api.services.impl;

import com.taskcontrol.api.dto.request.TaskRequest;
import com.taskcontrol.api.dto.request.TaskUpdateRequest;
import com.taskcontrol.api.dto.response.AppResponse;
import com.taskcontrol.api.dto.response.DeleteResponse;
import com.taskcontrol.api.dto.response.TaskResponse;
import com.taskcontrol.api.exceptions.TaskNotFoundException;
import com.taskcontrol.api.mapper.AppMapper;
import com.taskcontrol.api.mapper.TaskMapper;
import com.taskcontrol.api.persistence.model.Task;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import com.taskcontrol.api.persistence.repository.TaskRepository;
import com.taskcontrol.api.services.ITaskService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public AppResponse allTasks(Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAll(pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse allTasksByStatus(TaskStatus status, Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByStatus(status, pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse allTasksByCategory(TaskCategory category, Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByCategory(category, pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse allTasksByCategoryAndStatus(TaskCategory category, TaskStatus status,
      Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByCategoryAndStatus(category, status, pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse tasksByDate(LocalDate date, Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByDate(date, pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse taskByDateAfter(LocalDate date, Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByDateAfter(date, pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse taskByDateBefore(LocalDate date, Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByDateBefore(date, pageable);
    return AppMapper.toAppResponse(taskPage);
  }

  @Override
  public AppResponse taskByDateBetween(LocalDate afterDate, LocalDate beforeDate,
      Pageable pageable) {
    Page<Task> taskPage = taskRepository.findAllByDateBetween(afterDate, beforeDate, pageable);
    return AppMapper.toAppResponse(taskPage);
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
