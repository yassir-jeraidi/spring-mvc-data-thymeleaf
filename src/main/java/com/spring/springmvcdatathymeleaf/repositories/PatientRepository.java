package com.spring.springmvcdatathymeleaf.repositories;

import com.spring.springmvcdatathymeleaf.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByNomLike(String nom, Pageable pageable);
}