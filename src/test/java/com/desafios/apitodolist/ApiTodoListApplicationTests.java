package com.desafios.apitodolist;

import com.desafios.apitodolist.application.service.TaskService;
import com.desafios.apitodolist.domain.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiTodoListApplicationTests {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


}
