package dev.patika.veterinaryManagement.mapper;

import dev.patika.veterinaryManagement.dto.request.AppointmentRequest;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    AppointmentResponse asOutput(Appointment appointment);
    List<AppointmentResponse> asOutput(List<Appointment> appointment);
    Appointment asEntity(AppointmentRequest appointmentRequest);

    void update(@MappingTarget Appointment entity, AppointmentRequest request);
}
