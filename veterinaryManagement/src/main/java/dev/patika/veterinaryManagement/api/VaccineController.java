package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.dto.request.VaccineRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.service.VaccineService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/vaccines")
@AllArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse createVaccine(@RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.createVaccine(vaccineRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse VaccineUpdate(@PathVariable Long id, @RequestBody VaccineRequest request) {
        return vaccineService.vaccineUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void VaccineDelete(@PathVariable("id") Long id) {
        vaccineService.vaccineDeleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> findAll() {
        return vaccineService.findAll();
    }

    @GetMapping("/vaccines-upcoming")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AnimalResponse>> getAnimalsWithUpcomingVaccines(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<AnimalResponse> animals = vaccineService.getAnimalsWithUpcomingVaccines(startDate, endDate);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse getVaccineById(@PathVariable Long id) {
        return vaccineService.getVaccineById(id);
    }

    @GetMapping("/{animalId}/vaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VaccineResponse>> getVaccinesByAnimalId(@PathVariable Long animalId) {
        List<VaccineResponse> vaccines = vaccineService.getVaccinesByAnimalId(animalId);
        return ResponseEntity.ok(vaccines);
    }
}
