package dev.patika.veterinaryManagement.mapper;

import dev.patika.veterinaryManagement.dto.request.VaccineRequest;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {
    VaccineResponse asOutput(Vaccine vaccine);
    List<VaccineResponse> asOutput(List<Vaccine> vaccine);
    Vaccine asEntity(VaccineRequest vaccineRequest);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
