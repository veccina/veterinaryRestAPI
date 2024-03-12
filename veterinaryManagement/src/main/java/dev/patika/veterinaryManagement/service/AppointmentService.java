package dev.patika.veterinaryManagement.service;

import dev.patika.veterinaryManagement.dto.request.AppointmentRequest;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.entities.Doctor;
import dev.patika.veterinaryManagement.mapper.AppointmentMapper;
import dev.patika.veterinaryManagement.dao.IAppointmentRepo;
import dev.patika.veterinaryManagement.dao.IAvailableDateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final IAvailableDateRepo availableDateRepo;


    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        LocalDateTime requestedDateTime = appointmentRequest.getAppointmentDate();
        Doctor requestedDoctor = appointmentRequest.getDoctor();

        if (!isDoctorAvailable(requestedDoctor, requestedDateTime)) {
            throw new RuntimeException("Doktorun çalışma günü değil.");
        }

        if (isAppointmentTimeTaken(requestedDoctor, requestedDateTime)) {
            throw new RuntimeException("Doktorun bu saatte randevusu var.");
        }

        return appointmentMapper.asOutput(appointmentRepo.save(appointmentMapper.asEntity(appointmentRequest)));
    }

    public List<AppointmentResponse> findAll() {
        return appointmentMapper.asOutput(appointmentRepo.findAll());
    }

    public AppointmentResponse getAppointmentById(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            return appointmentMapper.asOutput(optionalAppointment.get());
        } else {
            throw new RuntimeException("Bu id ile herhangi bir randevu yok " + id);
        }
    }

    public AppointmentResponse appointmentUpdate(Long id, AppointmentRequest request) {
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);
        Optional<Appointment> isAppointmentExist = appointmentRepo.findByAppointmentDateAndAnimalIdAndDoctorId(
                request.getAppointmentDate(),
                request.getAnimal().getId(),
                request.getDoctor().getId());

        if (appointmentFromDb.isEmpty()) {
            throw new RuntimeException(id + " Bu id ile herhangi bir randevu bulunamadı.");
        }

        if (isAppointmentExist.isPresent()) {
            throw new RuntimeException(id + " Böyle bir randevu zaten var.");
        }
        Appointment appointment = appointmentFromDb.get();
        appointmentMapper.update(appointment, request);
        return appointmentMapper.asOutput(appointmentRepo.save(appointment));

    }


    private boolean isDoctorAvailable(Doctor doctor, LocalDateTime requestedDateTime) {
        List<AvailableDate> availableDates = availableDateRepo.findByDoctorId(doctor.getId());

        if (availableDates != null && !availableDates.isEmpty()) {
            DayOfWeek requestedDayOfWeek = requestedDateTime.getDayOfWeek();

            boolean isWorkingDay = availableDates.stream()
                    .anyMatch(date -> date.getAvailableDate().getDayOfWeek().equals(requestedDayOfWeek));

            return isWorkingDay;
        }
        return false;
    }


    private boolean isAppointmentTimeTaken(Doctor doctor, LocalDateTime requestedDateTime) {
        LocalDateTime startOfDay = requestedDateTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59);
        LocalDateTime startOfHour = requestedDateTime.withMinute(0).withSecond(0);
        LocalDateTime endOfHour = startOfHour.plusHours(1);

        List<Appointment> doctorAppointmentsForDay = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(
                doctor.getId(), startOfDay, endOfDay);

        List<Appointment> doctorAppointmentsForHour = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(
                doctor.getId(), startOfHour, endOfHour);

        boolean isAppointmentTakenForDay = doctorAppointmentsForDay.stream()
                .map(Appointment::getAppointmentDate)
                .anyMatch(appointmentDateTime -> appointmentDateTime.toLocalTime().equals(requestedDateTime.toLocalTime()));

        boolean isAppointmentTakenForHour = !doctorAppointmentsForHour.isEmpty();

        return isAppointmentTakenForDay || isAppointmentTakenForHour;
    }

    public List<AppointmentResponse> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate) {
        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        return appointmentMapper.asOutput(appointments);
    }


    public List<AppointmentResponse> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDate startDate, LocalDate endDate) {
        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        return appointmentMapper.asOutput(appointments);
    }

    public void appointmentDeleteById(Long id) {
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);
        if (appointmentFromDb.isPresent()) {
            appointmentRepo.delete(appointmentFromDb.get());
        } else {
            throw new RuntimeException(id + " Bu id ile herhangi bir randevu bulunamadı.");
        }
    }
}
