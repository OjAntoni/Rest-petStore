package com.example.petstore.dao;

import com.example.petstore.entity.Pet;

import java.util.List;

public interface PetDao {
    Pet save(Pet pet);
    void update(Pet pet);
    List<Pet> getAll();
    Pet findById(long id);
    void delete(long id);
}
