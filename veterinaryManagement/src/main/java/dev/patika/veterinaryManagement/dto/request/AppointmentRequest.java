package dev.patika.veterinaryManagement.dto.request;

import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
