package com.taskcontrol.api.controller;

import com.taskcontrol.api.dto.request.TaskRequest;
import com.taskcontrol.api.dto.request.TaskUpdateRequest;
import com.taskcontrol.api.dto.response.AppResponse;
import com.taskcontrol.api.dto.response.DeleteResponse;
import com.taskcontrol.api.dto.response.TaskResponse;
import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import com.taskcontrol.api.services.ITaskService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final ITaskService taskService;

  @Autowired
  public TaskController(ITaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public ResponseEntity<AppResponse> findAll(
      @RequestParam(required = false) TaskCategory category,
      @RequestParam(required = false) TaskStatus status,
      @RequestParam(required = false) LocalDate date,
      @RequestParam(required = false) LocalDate after,
      @RequestParam(required = false) LocalDate before,
      @PageableDefault(sort = {"id"}) Pageable pageable

  ) {
    AppResponse taskResponseList;

    if (category != null && status != null) {
      taskResponseList = taskService.allTasksByCategoryAndStatus(category, status, pageable);
    } else if (after != null && before != null) {
      taskResponseList = taskService.taskByDateBetween(after, before, pageable);
    } else if (date != null && after == null && before == null) {
      taskResponseList = taskService.tasksByDate(date, pageable);
    } else if (date == null && after != null) {
      taskResponseList = taskService.taskByDateAfter(after, pageable);
    } else if (date == null && before != null) {
      taskResponseList = taskService.taskByDateBefore(before, pageable);
    } else if (category != null) {
      taskResponseList = taskService.allTasksByCategory(category, pageable);
    } else if (status != null) {
      taskResponseList = taskService.allTasksByStatus(status, pageable);
    } else {
      taskResponseList = taskService.allTasks(pageable);
    }

    return ResponseEntity.ok(taskResponseList);
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskResponse> findTask(@PathVariable Long taskId) {
    return ResponseEntity.ok(taskService.taskById(taskId));
  }

  @PostMapping
  public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskRequest request) {
    return ResponseEntity.ok(taskService.saveTask(request));
  }

  @PutMapping("/{taskId}")
  public ResponseEntity<TaskResponse> updateTask(
      @PathVariable Long taskId,
      @RequestBody @Valid TaskUpdateRequest request
  ) {

    return ResponseEntity.ok(taskService.updateTask(taskId, request));
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity<DeleteResponse> deleteTask(@PathVariable Long taskId) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .body(taskService.deleteTask(taskId));
  }
}
