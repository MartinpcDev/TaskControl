package com.taskcontrol.api.mapper;

import com.taskcontrol.api.dto.request.TaskRequest;
import com.taskcontrol.api.dto.request.TaskUpdateRequest;
import com.taskcontrol.api.dto.response.TaskResponse;
import com.taskcontrol.api.persistence.model.Task;
import java.util.List;

public class TaskMapper {

  public static TaskResponse toTaskResponse(Task task) {
    if (task == null) {
      return null;
    }

    return new TaskResponse(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getStatus(),
        task.getCategory(),
        task.getDate()
    );
  }

  public static List<TaskResponse> toTaskListResponse(List<Task> tasks) {
    if (tasks == null) {
      return null;
    }

    return tasks.stream()
        .map(TaskMapper::toTaskResponse)
        .toList();
  }

  public static Task toTaskEntity(TaskRequest taskRequest) {
    if (taskRequest == null) {
      return null;
    }

    Task task = new Task();
    task.setTitle(taskRequest.title());
    task.setDescription(taskRequest.description());
    task.setStatus(taskRequest.status());
    task.setCategory(taskRequest.category());
    task.setDate(taskRequest.date());

    return task;
  }

  public static void updateTask(Task oldTask, TaskUpdateRequest request) {
    if (oldTask != null && request != null) {
      if (request.title() != null) {
        oldTask.setTitle(request.title());
      }
      if (request.description() != null) {
        oldTask.setDescription(request.description());
      }
      if (request.status() != null) {
        oldTask.setStatus(request.status());
      }
      if (request.category() != null) {
        oldTask.setCategory(request.category());
      }
      if (request.date() != null) {
        oldTask.setDate(request.date());
      }

    }
  }
}
