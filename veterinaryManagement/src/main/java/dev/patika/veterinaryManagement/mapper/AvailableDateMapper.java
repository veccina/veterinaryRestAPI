package dev.patika.veterinaryManagement.mapper;

import dev.patika.veterinaryManagement.dto.request.AvailableDateRequest;
import dev.patika.veterinaryManagement.dto.response.AvailableDateResponse;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper

public interface AvailableDateMapper {
    AvailableDateResponse asOutput(AvailableDate availableDate);

    AvailableDate asEntity(AvailableDateRequest availableDateRequest);

    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);

    default List<AvailableDateResponse> asOutput(List<AvailableDate> availableDates) {
        return availableDates.stream()
                .map(this::asOutput)
                .collect(Collectors.toList());
    }
}
