package com.spring.springmvcdatathymeleaf.controllers;

import com.spring.springmvcdatathymeleaf.dtos.PatientRequestDto;
import com.spring.springmvcdatathymeleaf.dtos.PatientResponseDto;
import com.spring.springmvcdatathymeleaf.services.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final String REDIRECT_TO_PATIENTS_PAGE = "redirect:/patients";
    private final String CREATE_OR_UPDATE_PATIENT_PAGE = "patients/create-or-update";
    private final String LIST_PATIENTS_PAGE = "patients/index";
    private final String VIEW_PATIENT_PAGE = "patients/view";

    @GetMapping
    public String getPatients(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "asc") String sortDirection,
                              @RequestParam(required = false) String keyword) {
        Page<PatientResponseDto> patientPage = patientService.getPatientByKeyword(page, size, sortField, sortDirection, keyword);

        int currentPage = patientPage.getNumber();
        int totalPages = patientPage.getTotalPages();
        long totalItems = patientPage.getTotalElements();
        int startPage = Math.max(0, currentPage - 2);
        int endPage = Math.min(totalPages - 1, currentPage + 2);
        List<Integer> pageNumbers = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        long end = Math.min((long) page * size + patientPage.getContent().size(), totalItems);

        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("size", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("end", end);
        model.addAttribute("pageNumbers", pageNumbers);

        return LIST_PATIENTS_PAGE;
    }

    @GetMapping("/create")
    public String createPatient(Model model) {
        model.addAttribute("isCreatePage", true);
        model.addAttribute("patient", new PatientRequestDto("", null, 0, false));
        return CREATE_OR_UPDATE_PATIENT_PAGE;
    }

    @GetMapping("/{id}")
    public String viewPatient(Model model, @PathVariable Long id) {
        PatientResponseDto patient = patientService.getById(id);
        model.addAttribute("patient", patient);
        return VIEW_PATIENT_PAGE;
    }

    @PostMapping
    public String savePatient(Model model, @Valid @ModelAttribute("patient") PatientRequestDto patient, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            model.addAttribute("isCreatePage", true);
            model.addAttribute("patient", patient);
            return CREATE_OR_UPDATE_PATIENT_PAGE;
        }
        patientService.save(patient);
        redirectAttributes.addFlashAttribute("message", "Patient saved successfully");
        return REDIRECT_TO_PATIENTS_PAGE;
    }

    @GetMapping("/edit/{id}")
    public String editPatient(Model model, @PathVariable Long id) {
        PatientResponseDto patientResponse = patientService.getById(id);
        PatientRequestDto patientRequest = new PatientRequestDto(
                patientResponse.nom(),
                patientResponse.dateNaissance(),
                patientResponse.score(),
                patientResponse.malade()
        );
        model.addAttribute("patient", patientRequest);
        model.addAttribute("patientId", id);
        model.addAttribute("isCreatePage", false);
        return CREATE_OR_UPDATE_PATIENT_PAGE;
    }

    @PutMapping("/{id}")
    public String updatePatient(Model model, @PathVariable Long id,
                                @Valid @ModelAttribute("patient") PatientRequestDto patientRequestDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isCreatePage", false);
            model.addAttribute("patient", patientRequestDto);
            return CREATE_OR_UPDATE_PATIENT_PAGE;
        }
        patientService.updateById(id, patientRequestDto);
        redirectAttributes.addFlashAttribute("message", "Patient updated successfully");
        return REDIRECT_TO_PATIENTS_PAGE;
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        patientService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Patient deleted successfully");
        return REDIRECT_TO_PATIENTS_PAGE;
    }
}
