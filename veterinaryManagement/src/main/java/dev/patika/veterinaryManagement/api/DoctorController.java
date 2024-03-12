package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.dto.request.DoctorRequest;
import dev.patika.veterinaryManagement.dto.response.DoctorResponse;
import dev.patika.veterinaryManagement.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/doctors")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse createDoctor(@RequestBody DoctorRequest doctorRequest) {
        return doctorService.createDoctor(doctorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse doctorUpdate(@PathVariable Long id, @RequestBody DoctorRequest request) {
        return doctorService.doctorUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void doctorDelete(@PathVariable("id") Long id) {
        doctorService.doctorDeleteById(id);
    }

}
