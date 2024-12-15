package com.taskcontrol.api.mapper;

import com.taskcontrol.api.dto.response.AppResponse;
import com.taskcontrol.api.persistence.model.Task;
import org.springframework.data.domain.Page;

public class AppMapper {

  public static AppResponse toAppResponse(Page<Task> tasks) {
    if (tasks == null) {
      return null;
    }

    return new AppResponse(
        TaskMapper.toTaskListResponse(tasks.getContent()),
        tasks.getPageable().getPageNumber(),
        tasks.getPageable().getPageSize(),
        (int) tasks.getTotalElements()
    );
  }
}
