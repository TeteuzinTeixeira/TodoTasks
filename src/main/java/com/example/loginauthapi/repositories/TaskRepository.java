package com.example.loginauthapi.repositories;

import com.example.loginauthapi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> getTaskByUserId(String userId);
}
