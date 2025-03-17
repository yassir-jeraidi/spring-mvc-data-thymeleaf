package com.spring.springmvcdatathymeleaf.services;

import com.spring.springmvcdatathymeleaf.dtos.PatientRequestDto;
import com.spring.springmvcdatathymeleaf.dtos.PatientResponseDto;
import com.spring.springmvcdatathymeleaf.entities.Patient;
import com.spring.springmvcdatathymeleaf.mappers.PatientMapper;
import com.spring.springmvcdatathymeleaf.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientResponseDto> getAll() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.patientsToPatientResponseDtos(patients);
    }

    public Page<PatientResponseDto> getAllPaginated(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Patient> patientPage = patientRepository.findAll(pageable);

        return patientMapper.patientPageToPatientResponseDtoPage(patientPage);
    }


    public Page<PatientResponseDto> getPatientByKeyword(int page, int size, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Patient> patientPage;
        if (keyword != null && !keyword.trim().isEmpty()) {
            patientPage = patientRepository.findByNomLike("%" + keyword.trim() + "%", pageable);
        } else {
            patientPage = patientRepository.findAll(pageable);
        }
        return patientMapper.patientPageToPatientResponseDtoPage(patientPage);
    }

    public PatientResponseDto getById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Patient not found")
        );
        return patientMapper.patientToPatientResponseDto(patient);
    }

    public PatientResponseDto save(PatientRequestDto patientRequestDto) {
        Patient savedPatient = patientRepository.save(patientMapper.patientRequestDtoToPatient(patientRequestDto));
        return patientMapper.patientToPatientResponseDto(savedPatient);
    }

    public PatientResponseDto updateById(Long id, PatientRequestDto patientRequestDto) {
        Patient patientToUpdate = patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Patient not found")
        );
        Patient savedPatient = patientRepository.save(patientMapper.patientRequestDtoToPatient(patientRequestDto));
        return patientMapper.patientToPatientResponseDto(savedPatient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}