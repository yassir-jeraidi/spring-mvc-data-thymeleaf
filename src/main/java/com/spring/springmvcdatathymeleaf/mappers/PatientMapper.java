package com.spring.springmvcdatathymeleaf.mappers;

import com.spring.springmvcdatathymeleaf.dtos.PatientRequestDto;
import com.spring.springmvcdatathymeleaf.dtos.PatientResponseDto;
import com.spring.springmvcdatathymeleaf.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientResponseDto patientToPatientResponseDto(Patient patient);
    Patient patientRequestDtoToPatient(PatientRequestDto patientRequestDto);
    java.util.List<PatientResponseDto> patientsToPatientResponseDtos(java.util.List<Patient> patients);
    default Page<PatientResponseDto> patientPageToPatientResponseDtoPage(Page<Patient> patientPage) {
        return patientPage.map(this::patientToPatientResponseDto);
    }
}