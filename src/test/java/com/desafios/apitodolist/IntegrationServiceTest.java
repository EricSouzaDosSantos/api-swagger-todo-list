package com.desafios.apitodolist;

import com.desafios.apitodolist.application.dto.task.TaskRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void mustRegisterValidService() throws Exception {
        var dto = new TaskRequestDTO(
                "Lavar a louça",
                "Lavar a louça as 14:00",
                "HIGH",
                true,
                LocalDateTime.now().plusMinutes(10)
        );

        mockMvc.perform(
                        post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("Lavar a louça as 14:00"));
    }

    @Test
    void mustRegisterInvalidService() throws Exception {
        var dto = new TaskRequestDTO(
                "Lavar a louça",
                "Lavar a louça as 14:00",
                "HIGH",
                true,
                LocalDateTime.now()
        );

        mockMvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Expiration date cannot be in the past"));
    }

    @Test
    void mustUpdateValidService() throws Exception {
        var dto = new TaskRequestDTO(
                "Lavar a louça",
                "Lavar a louça as 14:00",
                "HIGH",
                true,
                LocalDateTime.now().plusMinutes(10)
        );

        mockMvc.perform(
                put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void mustUpdateInvalidService() throws Exception {
        var dto = new TaskRequestDTO(
                "Lavar a louça",
                "Lavar a louça as 14:00",
                "HIGH",
                true,
                LocalDateTime.now()
        );

        mockMvc.perform(
                put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Expiration date cannot be in the past"));
    }
}
