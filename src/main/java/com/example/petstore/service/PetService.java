package com.example.petstore.service;

import com.example.petstore.entity.Pet;
import com.example.petstore.repository.PetRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public void update(Pet pet){
        petRepository.save(new Pet(pet));
    }

    public List<Pet> getAll(){
        return petRepository.findAll();
    }

    public Optional<Pet> find(long id){
        return petRepository.findById(id);
    }

    public void delete(long id){
        petRepository.deleteById(id);
    }


}
