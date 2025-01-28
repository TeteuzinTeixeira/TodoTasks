package com.example.loginauthapi.mapper;

import com.example.loginauthapi.domain.Task;
import com.example.loginauthapi.dto.TaskRequestDTO;
import com.example.loginauthapi.dto.TaskResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task TaskRequestDTOtoEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTask(dto.task());
        task.setStatus(dto.status());
        task.setUserId(dto.userId());
        return task;
    }

    public TaskResponseDTO TasktoTaskRensponseDTO(Task task) {
        return new TaskResponseDTO(task.getTask(), task.getUserId(), task.getStatus());
    }
}
