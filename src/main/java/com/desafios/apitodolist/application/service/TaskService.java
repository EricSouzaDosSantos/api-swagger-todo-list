package com.desafios.apitodolist.application.service;

import com.desafios.apitodolist.application.dto.task.TaskRequestDTO;
import com.desafios.apitodolist.application.dto.task.TaskResponseDTO;
import com.desafios.apitodolist.domain.entity.Task;
import com.desafios.apitodolist.domain.enums.TaskPriority;
import com.desafios.apitodolist.domain.exception.task.TaskNotFoundException;
import com.desafios.apitodolist.domain.repository.TaskRepository;
import com.desafios.apitodolist.domain.validation.ValidadeTaskData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ValidadeTaskData validadeTaskData;

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        validadeTaskData.validadeData(taskRequestDTO);
        Task task = new Task();
        task.setTitle(taskRequestDTO.title());
        task.setDescription(taskRequestDTO.description());
        task.setPriority(TaskPriority.valueOf(taskRequestDTO.priority()));
        task.setCompleted(task.isCompleted());
        task.setCreatedAt(LocalDateTime.now());
        task.setExpiredAt(taskRequestDTO.expiredAt());
        taskRepository.save(task);

        return builderTaskResponseDTO(task);
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        return builderTaskResponseDTO(task);
    }

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::builderTaskResponseDTO)
                .toList();
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        validadeTaskData.validadeData(taskRequestDTO);

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        task.setTitle(taskRequestDTO.title());
        task.setDescription(taskRequestDTO.description());
        task.setPriority(TaskPriority.valueOf(taskRequestDTO.priority()));
        task.setCompleted(taskRequestDTO.completed());
        taskRepository.save(task);

        return builderTaskResponseDTO(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskResponseDTO> getTasksByPriority(TaskPriority priority) {
        List<Task> tasks = taskRepository.findByPriority(priority);
        return tasks.stream()
                .map(this::builderTaskResponseDTO)
                .toList();
    }

    public TaskResponseDTO builderTaskResponseDTO(Task task) {
        return new TaskResponseDTO(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.isCompleted(),
                task.getCreatedAt(),
                task.getExpiredAt());
    }

}
