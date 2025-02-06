package com.example.loginauthapi.dto;

import com.example.loginauthapi.domain.Status;

public record TaskResponseDTO(String id, String task, Status status) {
}
