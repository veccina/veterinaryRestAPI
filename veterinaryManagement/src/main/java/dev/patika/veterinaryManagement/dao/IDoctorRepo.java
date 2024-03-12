package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByNameAndPhoneAndMailAndAddressAndCity(String name, String phone, String mail, String address, String city);
}
