package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.dto.request.CustomerRequest;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.service.AnimalService;
import dev.patika.veterinaryManagement.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AnimalService animalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse customerUpdate(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return customerService.customerUpdate(id, request);
    }
    @GetMapping("/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse findByName(@PathVariable String name) {
        return customerService.findByName(name);
    }


    @GetMapping("/{customerId}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Animal>> getAnimalsByOwnerId(@PathVariable Long customerId) {
        List<Animal> animals = animalService.getAnimalsByCustomerId(customerId);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void customerDelete(@PathVariable("id") Long id) {
        customerService.customerDeleteById(id);
    }

}
