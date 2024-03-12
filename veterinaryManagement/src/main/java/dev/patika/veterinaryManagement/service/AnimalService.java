package dev.patika.veterinaryManagement.service;

import dev.patika.veterinaryManagement.dto.request.AnimalRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.mapper.AnimalMapper;
import dev.patika.veterinaryManagement.dao.IAnimalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final IAnimalRepo animalRepo;
    private final AnimalMapper animalMapper;
    private final CustomerService customerService;

    public AnimalResponse createAnimal(AnimalRequest animalRequest) {
        Optional<Animal> optionalAnimal = animalRepo.findByName(animalRequest.getName());
        if (optionalAnimal.isPresent()) {
            throw new RuntimeException(" Bu isimde hayvan zaten var" + animalRequest.getName());
        }
        return animalMapper.asOutput(animalRepo.save(animalMapper.asEntity(animalRequest)));
    }

    public AnimalResponse findByName(String name) {
        Optional<Animal> optionalAnimal = animalRepo.findByName(name);
        return animalMapper.asOutput(optionalAnimal.get());
    }

    public List<AnimalResponse> findAll() {
        return animalMapper.asOutput(animalRepo.findAll());
    }

    public AnimalResponse animalUpdate(Long id, AnimalRequest request) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        Optional<Animal> isAnimalExist = animalRepo.findByName(request.getName());

        if (animalFromDb.isEmpty()) {
            throw new RuntimeException(id + " Bu id'ye ait hayvan bulunamadı.");
        }

        if (isAnimalExist.isPresent()) {
            throw new RuntimeException(id + " Bu hayvan zaten sistemde kayıtlı.");
        }
        Animal animal = animalFromDb.get();
        animalMapper.update(animal, request);
        return animalMapper.asOutput(animalRepo.save(animal));

    }


    public AnimalResponse getAnimalById(Long id) {
        Optional<Animal> optionalAnimal = animalRepo.findById(id);
        if (optionalAnimal.isPresent()) {
            return animalMapper.asOutput(optionalAnimal.get());
        } else {
            throw new RuntimeException("Bu id'de hayvan bulunamadı : " + id);
        }
    }

    public List<Animal> getAnimalsByCustomerId(Long customerId) {
        return animalRepo.findAllByCustomerId(customerId);
    }

    public void animalDeleteById(Long id) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        if (animalFromDb.isPresent()) {
            animalRepo.delete(animalFromDb.get());
        } else {
            throw new RuntimeException(id + " Bu id'ye ait hayvan bulunamadı.");
        }
    }
}
