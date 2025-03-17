package com.spring.springmvcdatathymeleaf.repositories;

import com.spring.springmvcdatathymeleaf.dtos.PatientResponseDto;
import com.spring.springmvcdatathymeleaf.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}