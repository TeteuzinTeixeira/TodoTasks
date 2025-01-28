package com.example.loginauthapi.service;

import com.example.loginauthapi.domain.Task;
import com.example.loginauthapi.domain.User;
import com.example.loginauthapi.dto.TaskRequestDTO;
import com.example.loginauthapi.dto.TaskResponseDTO;
import com.example.loginauthapi.dto.TaskUpdateRequestDTO;
import com.example.loginauthapi.mapper.TaskMapper;
import com.example.loginauthapi.repositories.TaskRepository;
import com.example.loginauthapi.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public ResponseEntity createTask(TaskRequestDTO body) {
        Optional<User> user = userRepository.findById(body.userId());
        if (user == null)
            return ResponseEntity.badRequest().body("Usuario não encontrado para o Id informado!");
        if (!validateTask(body)) {
            return ResponseEntity.badRequest().body("Preencha todos os campos corretamente!");
        }
        Task task = taskMapper.TaskRequestDTOtoEntity(body);
        taskRepository.save(task);
        return ResponseEntity.ok().body(task);
    }

    public ResponseEntity deleteTask(String id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

    public List<TaskResponseDTO> getTask(String userId) {
        List<Task> tasks = taskRepository.getTaskByUserId(userId);
        List<TaskResponseDTO> taskResponseDTOs = new ArrayList<>();
        tasks.forEach(task -> taskResponseDTOs.add(taskMapper.TasktoTaskRensponseDTO(task)));
        return taskResponseDTOs;
    }

    private boolean validateTask(TaskRequestDTO body) {
        if (body.task() == null || body.task().isEmpty()) {
            return false;
        }
        if (body.userId() == null || body.userId().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validateUpdateTask(TaskUpdateRequestDTO body) {
        if (body.task() == null || body.task().isEmpty()) {
            return false;
        }
        if (body.userId() == null || body.userId().isEmpty()) {
            return false;
        }
        return true;
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    public ResponseEntity updateTask(TaskUpdateRequestDTO body) {
        Optional<User> user = userRepository.findById(body.userId());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado para o Id informado!");
        }
        if (!validateUpdateTask(body)) {
            return ResponseEntity.badRequest().body("Preencha todos os campos corretamente!");
        }
        Optional<Task> existingTask = taskRepository.findById(body.id());
        if (existingTask.isEmpty()) {
            return ResponseEntity.badRequest().body("Tarefa não encontrada para o Id informado!");
        }
        Task task = existingTask.get();
        task.setTask(body.task());
        task.setStatus(body.status());
        task.setUserId(body.userId());
        taskRepository.save(task);
        return ResponseEntity.ok().body(task);
    }
}
