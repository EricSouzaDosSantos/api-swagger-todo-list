package com.desafios.apitodolist.domain.service;

import com.desafios.apitodolist.domain.entity.Task;
import com.desafios.apitodolist.domain.enums.TaskPriority;
import com.desafios.apitodolist.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskSchedulerService {
    private final TaskRepository taskRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTaskPriority() {
        List<Task> tasks = taskRepository.findByCompleted(false);
        LocalDateTime now = LocalDateTime.now();

        for (Task task : tasks){

            if ( task.getExpiredAt().isBefore(now)) {
                if (task.getPriority().ordinal() == TaskPriority.URGENT.ordinal()){
                    System.out.println("Task " + task.getId() + " is already at the highest priority: " + task.getPriority());
                    continue;
                }
                task.setPriority(TaskPriority.values()[task.getPriority().ordinal() - 1]);
                taskRepository.save(task);
            }

        }
    }
}
