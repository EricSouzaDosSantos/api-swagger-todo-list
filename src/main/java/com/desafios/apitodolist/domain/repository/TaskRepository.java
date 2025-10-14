package com.desafios.apitodolist.domain.repository;

import com.desafios.apitodolist.domain.entity.Task;
import com.desafios.apitodolist.domain.enums.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByCompleted(boolean completed);
}
