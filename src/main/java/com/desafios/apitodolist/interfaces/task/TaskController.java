package com.desafios.apitodolist.interfaces.task;

import com.desafios.apitodolist.application.dto.task.TaskRequestDTO;
import com.desafios.apitodolist.application.dto.task.TaskResponseDTO;
import com.desafios.apitodolist.application.service.TaskService;
import com.desafios.apitodolist.domain.enums.TaskPriority;
import com.desafios.apitodolist.domain.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "Operations related to task management")
public class TaskController {

    private final TaskService taskService;

    @Operation(
            summary = "Create a new task",
            description = "Create a new task with the specified details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Details of the task to be created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                "id": 1,
                                                "title": "Complete API documentation",
                                                "description": "Ensure all endpoints are documented",
                                                "priority": "HIGH",
                                                "completed": false,
                                                "createdAt": "2023-10-01T12:00:00",
                                                "expiredAt": "2023-10-15T12:00:00"
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Task created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))),
            }
    )
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponse = taskService.createTask(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @Operation(
            summary = "Get all tasks",
            description = "Retrieve a list of all tasks.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Details of the all tasks to be taken",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                                [
                                                    {
                                                        "id": 1,
                                                        "title": "Complete API documentation",
                                                        "description": "Ensure all endpoints are documented",
                                                        "priority": "HIGH",
                                                        "completed": false,
                                                        "createdAt": "2023-10-01T12:00:00",
                                                        "expiredAt": "2023-10-15T12:00:00"
                                                    }
                                                ]
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of tasks retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No tasks found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))),
            }
    )
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        System.out.println("Fetching all tasks");
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Get Task By Id",
            description = "Get only onde task by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Details of the task to be taken",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                "id": 1,
                                                "title": "Complete API documentation",
                                                "description": "Ensure all endpoints are documented",
                                                "priority": "HIGH",
                                                "completed": false,
                                                "createdAt": "2023-10-01T12:00:00",
                                                "expiredAt": "2023-10-15T12:00:00"
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No task found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO taskResponse = taskService.getTaskById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(
            summary = "Get Tasks By status",
            description = "Get tasks by status",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Details of the task to be taken",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                                [
                                                    {
                                                        "id": 1,
                                                        "title": "Complete API documentation",
                                                        "description": "Ensure all endpoints are documented",
                                                        "priority": "HIGH",
                                                        "completed": false,
                                                        "createdAt": "2023-10-01T12:00:00",
                                                        "expiredAt": "2023-10-15T12:00:00"
                                                    }
                                                ]
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No task found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))),
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByPriority(@RequestParam("p") String priority) {
        System.out.println("Fetching tasks with priority: " + priority.toUpperCase());
        System.out.println("Test");
        List<TaskResponseDTO> tasks = taskService.getTasksByPriority(TaskPriority.valueOf(priority.toUpperCase()));
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "update Task By Id",
            description = "update only one task by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Details of the task to be updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                                    {
                                                        "id": 1,
                                                        "title": "Complete API documentation",
                                                        "description": "Ensure all endpoints are documented",
                                                        "priority": "HIGH",
                                                        "completed": false,
                                                        "createdAt": "2023-10-01T12:00:00",
                                                        "expiredAt": "2023-10-15T12:00:00"
                                                    }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "updated task successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponse = taskService.updateTask(id, taskRequestDTO);
        return ResponseEntity.ok(taskResponse);
    }

    @Operation(
            summary = "Delete Task By Id",
            description = "Delete a task by ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Details of the task to be deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task deleted successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
