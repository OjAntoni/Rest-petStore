package com.example.petstore.service;

import com.example.petstore.dao.PetDao;
import com.example.petstore.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PetService {
    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    public Pet save(Pet pet){
        return petDao.save(pet);
    }

    public void update(Pet pet){
        petDao.update(pet);
    }

    public List<Pet> getAll(){
        return petDao.getAll();
    }

    public Optional<Pet> find(long id){
        return Optional.ofNullable(petDao.findById(id));
    }

    public void delete(long id){
        petDao.delete(id);
    }
}
