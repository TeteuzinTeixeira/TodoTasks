package com.example.loginauthapi.controllers;

import com.example.loginauthapi.dto.TaskRequestDTO;
import com.example.loginauthapi.dto.TaskResponseDTO;
import com.example.loginauthapi.dto.TaskUpdateRequestDTO;
import com.example.loginauthapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskRequestDTO body){
        return taskService.createTask(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable String id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public List<TaskResponseDTO> getTasks(@PathVariable String id) {
        return taskService.getTask(id);
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestBody TaskUpdateRequestDTO body) {
        return taskService.updateTask(body);
    }
}
