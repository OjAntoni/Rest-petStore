package com.example.petstore.dao.InMemoryDao;

import com.example.petstore.dao.PetDao;
import com.example.petstore.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryPetDao implements PetDao {
    private static final List<Pet> pets = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong index = new AtomicLong();

    @Override
    public Pet save(Pet pet) {
        long currId = index.incrementAndGet();
        pet.setId(currId);
        pets.add(pet);
        return pet;
    }

    @Override
    public void update(Pet pet) {
        long id = pet.getId();
        int petIndex;
        for (int i = 0; i < pets.size(); i++) {
            if(pets.get(i).getId()==id){
                petIndex=i;
                pets.set(petIndex, new Pet(pet));
                break;
            }
        }
    }

    @Override
    public List<Pet> getAll() {
        return pets;
    }

    @Override
    public Pet findById(long id) {
        Pet pet = null;
        for (Pet pet1 : pets) {
            if(pet1.getId()==id){
                pet = pet1;
            }
        }
        return pet;
    }

    @Override
    public void delete(long id) {
        pets.removeIf(pet -> pet.getId()==id);
    }
}
