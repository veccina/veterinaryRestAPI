package dev.patika.veterinaryManagement.service;

import dev.patika.veterinaryManagement.dto.request.VaccineRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Vaccine;
import dev.patika.veterinaryManagement.mapper.AnimalMapper;
import dev.patika.veterinaryManagement.mapper.VaccineMapper;
import dev.patika.veterinaryManagement.dao.IVaccineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineService {
    private final IVaccineRepo vaccineRepo;
    private final VaccineMapper vaccineMapper;
    private final AnimalMapper animalMapper;


    public List<VaccineResponse> findAll() {
        return vaccineMapper.asOutput(vaccineRepo.findAll());
    }

    public VaccineResponse vaccineUpdate(Long id, VaccineRequest request) {
        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);
        Optional<Vaccine> isVaccineExist = vaccineRepo.findByNameAndCode(request.getName(), request.getCode());

        if (vaccineFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız aşı sistemde yok.");
        }

        if (isVaccineExist.isPresent()) {
            throw new RuntimeException(id + " Bu aşı daha önce sisteme kayıt olmuştur.");
        }
        Vaccine vaccine = vaccineFromDb.get();
        vaccineMapper.update(vaccine, request);
        return vaccineMapper.asOutput(vaccineRepo.save(vaccine));

    }

    public VaccineResponse getVaccineById(Long id) {
        Optional<Vaccine> optionalVaccine = vaccineRepo.findById(id);
        if (optionalVaccine.isPresent()) {
            return vaccineMapper.asOutput(optionalVaccine.get());
        } else {
            throw new RuntimeException("Bu id'de aşı bulunamadı: " + id);
        }
    }

    public VaccineResponse createVaccine(VaccineRequest vaccineRequest) {
        Optional<Vaccine> optionalVaccine = vaccineRepo.findByNameAndCode(vaccineRequest.getName(), vaccineRequest.getCode());

        if (optionalVaccine.isPresent()) {
            Vaccine existingVaccine = optionalVaccine.get();

            LocalDate currentDate = LocalDate.now();
            LocalDate finishDate = existingVaccine.getProtectionFinishDate();

            if (finishDate != null && finishDate.isAfter(currentDate)) {
                throw new RuntimeException("Bu aşı uygulanmış ve koruyuculuğu devam ediyor." + vaccineRequest.getName());
            }
        }
        return vaccineMapper.asOutput(vaccineRepo.save(vaccineMapper.asEntity(vaccineRequest)));
    }

    public void vaccineDeleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);
        if (vaccineFromDb.isPresent()) {
            vaccineRepo.delete(vaccineFromDb.get());
        } else {
            throw new RuntimeException(id + " Bu id'de aşı bulunamadı. Silme işlemi başarısız oldu.");
        }
    }

    public List<VaccineResponse> getVaccinesByAnimalId(Long animalId) {
        List<Vaccine> vaccines = vaccineRepo.findByAnimalId(animalId);
        return vaccineMapper.asOutput(vaccines);
    }

    public List<AnimalResponse> getAnimalsWithUpcomingVaccines(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> upcomingVaccines = vaccineRepo.findAnimalsWithUpcomingVaccines(startDate, endDate);
        List<Animal> animals = upcomingVaccines.stream().map(Vaccine::getAnimal).collect(Collectors.toList());
        return animalMapper.asOutput(animals);
    }
}
