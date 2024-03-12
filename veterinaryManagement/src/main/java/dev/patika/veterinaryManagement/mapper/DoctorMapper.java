package dev.patika.veterinaryManagement.mapper;

import dev.patika.veterinaryManagement.dto.request.DoctorRequest;
import dev.patika.veterinaryManagement.dto.response.DoctorResponse;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorResponse asOutput(Doctor animal);
    List<DoctorResponse> asOutput(List<Doctor> doctor);
    Doctor asEntity(DoctorRequest animalRequest);

    void update(@MappingTarget Doctor entity, DoctorRequest request);
}
