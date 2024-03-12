package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.dto.request.AnimalRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/animals")
@AllArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse createAnimal(@RequestBody AnimalRequest animalRequest) {
        return animalService.createAnimal(animalRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }


    @GetMapping("/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse findByName(@PathVariable String name) {
        return animalService.findByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse animalUpdate(@PathVariable Long id, @RequestBody AnimalRequest request) {
        return animalService.animalUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void animalDelete(@PathVariable("id") Long id) {
        animalService.animalDeleteById(id);
    }
}
