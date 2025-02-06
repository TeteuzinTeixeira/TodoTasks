package com.example.loginauthapi.dto;

import com.example.loginauthapi.domain.Status;

public record TaskUpdateRequestDTO(String id, String task, Status status, String userId) { }
