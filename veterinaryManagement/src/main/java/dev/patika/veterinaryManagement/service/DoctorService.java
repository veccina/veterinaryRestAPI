package dev.patika.veterinaryManagement.service;

import dev.patika.veterinaryManagement.dto.request.DoctorRequest;
import dev.patika.veterinaryManagement.dto.response.DoctorResponse;
import dev.patika.veterinaryManagement.entities.Doctor;
import dev.patika.veterinaryManagement.mapper.DoctorMapper;
import dev.patika.veterinaryManagement.dao.IDoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final IDoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {
        Optional<Doctor> isDoctorExist = doctorRepo.findByNameAndPhoneAndMailAndAddressAndCity(doctorRequest.getName(), doctorRequest.getPhone(), doctorRequest.getMail(), doctorRequest.getAddress(), doctorRequest.getCity());
        if (isDoctorExist.isPresent()){
            throw new RuntimeException("Bu isimde doktor zaten var" + doctorRequest.getName());
        }
        return doctorMapper.asOutput(doctorRepo.save(doctorMapper.asEntity(doctorRequest)));
    }

    public List<DoctorResponse> findAll() {
        return doctorMapper.asOutput(doctorRepo.findAll());
    }


    public DoctorResponse getDoctorById(Long id){
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
        if (optionalDoctor.isPresent()){
            return doctorMapper.asOutput(optionalDoctor.get());
        }else {
            throw new RuntimeException("Bu id'de doktor bulunamadı: " + id);
        }
    }

    public DoctorResponse doctorUpdate(Long id, DoctorRequest request) {
        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);
        Optional<Doctor> isDoctorExist = doctorRepo.findByNameAndPhoneAndMailAndAddressAndCity(request.getName(), request.getPhone(), request.getMail(), request.getAddress(), request.getCity());

        if (doctorFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız doktor sistemde yok.");
        }

        if (isDoctorExist.isPresent()) {
            throw new RuntimeException(id + " Bu doktor daha önce sisteme kayıt olmuştur.");
        }
        Doctor doctor = doctorFromDb.get();
        doctorMapper.update(doctor, request);
        return  doctorMapper.asOutput(doctorRepo.save(doctor));

    }

    public void doctorDeleteById(Long id) {
        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);
        if (doctorFromDb.isPresent()) {
            doctorRepo.delete(doctorFromDb.get());
        }else {
            throw new RuntimeException(id +"id'li doktor sistemde bulunamadı.");
        }
    }
}
