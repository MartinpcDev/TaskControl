package com.taskcontrol.api.dto.request;

import com.taskcontrol.api.persistence.model.TaskCategory;
import com.taskcontrol.api.persistence.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

public record TaskRequest(
    @NotBlank(message = "El title no puede ir vacio")
    @Length(min = 10, max = 60, message = "El title debe estar entre 10 a 60 caracteres")
    String title,
    @NotBlank(message = "El title no puede ir vacio")
    @Length(min = 10, max = 300, message = "La description debe estar entre 10 a 300 caracteres")
    String description,
    @NotNull(message = "El status no puede ir vacio")
    TaskStatus status,
    @NotNull(message = "La category no puede ir vacio")
    TaskCategory category,
    @NotBlank(message = "El date no puede ir vacio")
    LocalDate date
) {

}
