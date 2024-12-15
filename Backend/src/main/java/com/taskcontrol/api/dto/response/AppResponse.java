package com.taskcontrol.api.dto.response;

import java.util.List;

public record AppResponse(
    List<TaskResponse> tasks,
    Integer page,
    Integer size,
    Integer total
) {

}
