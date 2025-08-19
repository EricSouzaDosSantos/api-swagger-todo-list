package com.desafios.apitodolist.domain.validation;

import com.desafios.apitodolist.application.dto.task.TaskRequestDTO;
import com.desafios.apitodolist.domain.enums.TaskPriority;
import com.desafios.apitodolist.domain.exception.task.InvalidTaskDataException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ValidadeTaskData {

    public void validadeData(TaskRequestDTO taskRequestDTO) {
        if (taskRequestDTO.title() == null || taskRequestDTO.title().isBlank()) {
            throw new InvalidTaskDataException("Title is required");
        }

        if (taskRequestDTO.description() == null || taskRequestDTO.description().isBlank()) {
            throw new InvalidTaskDataException("Description is required");
        }

        if (taskRequestDTO.priority() == null) {
            throw new InvalidTaskDataException("Priority is required");
        }

        if (taskRequestDTO.expiredAt() == null) {
            throw new InvalidTaskDataException("Expiration date is required");
        }

        if (taskRequestDTO.expiredAt().isBefore(java.time.LocalDateTime.now())) {
            throw new InvalidTaskDataException("Expiration date cannot be in the past");
        }
        if (taskRequestDTO.priority().isBlank() || !taskRequestDTO.priority().matches(Arrays.stream(TaskPriority.values())
                .map(Enum::name)
                .reduce((a, b) -> a + "|" + b)
                .orElse(""))) {
            throw new InvalidTaskDataException("Priority must be one of: " + Arrays.toString(TaskPriority.values()));
        }
    }
}
