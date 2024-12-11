package com.taskcontrol.api.dto.request;

import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

public record TaskUpdateRequest(
    @Length(min = 10, max = 60, message = "El title debe estar entre 10 a 60 caracteres")
    String title,
    @Length(min = 10, max = 300, message = "La description debe estar entre 10 a 300 caracteres")
    String description,
    TaskStatus status,
    TaskCategory category,
    LocalDate date
) {

}
