package com.csc340.crud_api_jpa_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository; // Assuming you have an AnimalRepository interface

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }
    
    public Optional<Animal> getAnimalById(int id) {
        return animalRepository.findById(id);
    }
    public List<Animal> getAnimalsBySpecies(String species) {
        List<Animal> an = getAllAnimals();
        List<Animal> get = new ArrayList<>();
        for (Animal a : an) {
            if (a.getSpecies().equalsIgnoreCase(species)) {
                get.add(a);
            }
        }
        return get;
    }

    public List<Animal> getAnimalsBySearch(String search) {
        List<Animal> an = getAllAnimals();
        List<Animal> get = new ArrayList<>();
        for (Animal a : an) {
            if (a.getName().contains(search)) {
                get.add(a);
            }
        }
        return get;
    }


    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Optional<Animal> updateAnimal(int id, Animal input) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(input.getName());
            animal.setScientificName(input.getScientificName());
            animal.setSpecies(input.getSpecies());
            animal.setHabitat(input.getHabitat());
            animal.setDescription(input.getDescription());
            return animalRepository.save(animal);
        });
    }

    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }
}
