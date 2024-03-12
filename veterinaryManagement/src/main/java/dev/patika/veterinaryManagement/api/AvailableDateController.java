package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.dto.request.AvailableDateRequest;
import dev.patika.veterinaryManagement.dto.response.AvailableDateResponse;
import dev.patika.veterinaryManagement.service.AvailableDateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/available-dates")
@AllArgsConstructor
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse createAvailableDate(@RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.createAvailableDate(availableDateRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse availableDateUpdate(@PathVariable Long id, @RequestBody AvailableDateRequest request) {
        return availableDateService.availableDateUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void availableDateDelete(@PathVariable("id") Long id) {
        availableDateService.availableDateDeleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return availableDateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getAvailableDateById(@PathVariable Long id) {
        return availableDateService.getAvailableDateById(id);
    }

    @GetMapping("/doctors/{doctorId}/available-dates")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AvailableDateResponse>> getAvailableDatesByDoctorId(@PathVariable Long doctorId) {
        List<AvailableDateResponse> availableDates = availableDateService.getAvailableDatesByDoctorId(doctorId);
        return ResponseEntity.ok(availableDates);
    }
}
