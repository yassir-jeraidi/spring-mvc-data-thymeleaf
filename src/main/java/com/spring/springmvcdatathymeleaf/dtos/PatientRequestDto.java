package com.spring.springmvcdatathymeleaf.dtos;

import jakarta.validation.constraints.*;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public record PatientRequestDto(
        @NotBlank(message = "Name cannot be empty")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String nom,
        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dateNaissance,
        @Min(value = 0, message = "Score must be at least 0")
        @Max(value = 100, message = "Score cannot exceed 100")
        int score,
        boolean malade
)
        implements Serializable {
}