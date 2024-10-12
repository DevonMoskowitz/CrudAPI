package com.csc340.crud_api_jpa_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService; // Assuming you have a service class

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable int id) {
        Animal animal = animalService.getAnimalById(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable int id, @RequestBody Animal animalDetails) {
        Animal updatedAnimal = animalService.updateAnimal(id, animalDetails);
        if (updatedAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAnimal);
    }
    @GetMapping("/species/{species}")
    public List<Animal> getAnimalsBySpecies(@PathVariable String species) {
        System.out.println(species);
        return animalService.getAnimalsBySpecies(species);
    }
    @GetMapping("/search/{search}")
    public List<Animal> getAnimalsBySearch(@PathVariable String search) {
        return animalService.getAnimalsBySearch(search);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
