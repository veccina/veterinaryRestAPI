package dev.patika.veterinaryManagement.service;

import dev.patika.veterinaryManagement.dto.request.AvailableDateRequest;
import dev.patika.veterinaryManagement.dto.response.AvailableDateResponse;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.mapper.AvailableDateMapper;
import dev.patika.veterinaryManagement.dao.IAvailableDateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService {
    private final IAvailableDateRepo availableDateRepo;
    private final AvailableDateMapper availableDateMapper;

    public AvailableDateResponse createAvailableDate(AvailableDateRequest availableDateRequest) {
        LocalDate requestedDate = availableDateRequest.getAvailableDate();
        Long doctorId = availableDateRequest.getDoctor().getId();

        Optional<AvailableDate> existingAvailableDate = availableDateRepo.findByDoctorIdAndAvailableDate(doctorId, requestedDate);

        if (existingAvailableDate.isPresent()) {
            throw new RuntimeException("Bu doktora ait müsaitlik zaten mevcut: " + requestedDate);
        }

        Optional<AvailableDate> availableDatesOnRequestedDate = availableDateRepo.findByAvailableDate(requestedDate);

        if (!availableDatesOnRequestedDate.isEmpty()) {
            return availableDateMapper.asOutput(availableDateRepo.save(availableDateMapper.asEntity(availableDateRequest)));
        }
        return availableDateMapper.asOutput(availableDateRepo.save(availableDateMapper.asEntity(availableDateRequest)));
    }

    public List<AvailableDateResponse> findAll() {
        return availableDateMapper.asOutput(availableDateRepo.findAll());
    }

    public AvailableDateResponse getAvailableDateById(Long id) {
        Optional<AvailableDate> optionalAvailableDate = availableDateRepo.findById(id);
        if (optionalAvailableDate.isPresent()) {
            return availableDateMapper.asOutput(optionalAvailableDate.get());
        } else {
            throw new RuntimeException("Bu id ile müsait gün bulunmamaktadır :  " + id);
        }
    }

    public void availableDateDeleteById(Long id) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);
        if (availableDateFromDb.isPresent()) {
            availableDateRepo.delete(availableDateFromDb.get());
        } else {
            throw new RuntimeException(id + "id'li 'Doktor Müsaitliği' sistemde bulunamadı.");
        }
    }

    public List<AvailableDateResponse> getAvailableDatesByDoctorId(Long doctorId) {
        List<AvailableDate> availableDates = availableDateRepo.findByDoctorId(doctorId);
        return availableDateMapper.asOutput(availableDates);
    }

    public AvailableDateResponse availableDateUpdate(Long id, AvailableDateRequest request) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByAvailableDate(request.getAvailableDate());

        if (availableDateFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız 'Doktor Müsaitliği' sistemde yok.");
        }

        if (isAvailableDateExist.isPresent()) {
            throw new RuntimeException(id + " Bu 'Doktor Müsaitliği' daha önce sisteme kayıt olmuştur.");
        }
        AvailableDate availableDate = availableDateFromDb.get();
        availableDateMapper.update(availableDate, request);
        return availableDateMapper.asOutput(availableDateRepo.save(availableDate));
    }
}
