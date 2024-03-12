package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByName(String name);
    Optional<Customer> findByNameAndPhoneAndMailAndAddressAndCity(String name, String phone, String mail, String address, String city);
}
