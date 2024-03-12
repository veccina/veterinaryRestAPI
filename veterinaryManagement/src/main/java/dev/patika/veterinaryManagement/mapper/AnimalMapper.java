package dev.patika.veterinaryManagement.mapper;

import dev.patika.veterinaryManagement.dto.request.AnimalRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AnimalMapper {

    AnimalResponse asOutput(Animal animal);
    List<AnimalResponse> asOutput(List<Animal> animal);
    Animal asEntity(AnimalRequest animalRequest);

    void update(@MappingTarget Animal entity, AnimalRequest request);
}
