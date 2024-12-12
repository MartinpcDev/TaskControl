package com.taskcontrol.api.dto.response;

import java.util.List;

public record ExceptionResponse(
    String error,
    String message,
    List<String> details
) {

}
