package com.desafios.apitodolist.application.dto.task;

import com.desafios.apitodolist.domain.enums.TaskPriority;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Task response data transfer object")
public record TaskResponseDTO(

        @Schema(description = "Unique identifier of the task", example = "1")
        Long id,

        @Schema(description = "Title of the task", example = "Buy groceries")
        String title,

        @Schema(description = "Detailed description of the task", example = "Buy milk, bread, and eggs")
        String description,

        @Schema(description = "Priority of the task", example = "HIGH")
        TaskPriority priority,

        @Schema(description = "Indicates if the task is completed", example = "false")
        boolean completed,

        @Schema(description = "Date and time when the task was created", example = "2024-06-01T10:15:30")
        LocalDateTime createdAt,

        @Schema(description = "Date and time when the task is set to expire", example = "2024-06-10T10:15:30")
        LocalDateTime expiredAt
) {
}