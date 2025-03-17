package com.spring.springmvcdatathymeleaf.dtos;

import java.io.Serializable;
import java.util.Date;

public record PatientResponseDto(Long id, String nom, Date dateNaissance, boolean malade, int score)
        implements Serializable {
}