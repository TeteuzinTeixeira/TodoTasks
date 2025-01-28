package com.example.loginauthapi.dto;

import com.example.loginauthapi.domain.Status;

public record TaskRequestDTO(String task, Status status, String userId) { }
