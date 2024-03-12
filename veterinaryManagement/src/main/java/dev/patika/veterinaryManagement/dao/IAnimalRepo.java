package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal,Long> {
    Optional<Animal> findByName(String name);
    List<Animal> findAllByCustomerId(Long customerId);

}
