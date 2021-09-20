package com.example.petstore.resource;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.constants.PetStatus;
import com.example.petstore.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetResource {
    private PetService petService;

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody @Valid Pet pet, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(405).build();
        } else {
            Pet saved = petService.save(pet);
            return ResponseEntity.status(200).body(saved);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePet(@RequestBody @Valid Pet pet, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(405).build();
        } else {
            if (petService.find(pet.getId()).isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            petService.update(pet);
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> findByStatus(String status){
        if(!status.equals(PetStatus.AVAILABLE) &&
                !status.equals(PetStatus.SOLD) &&
                !status.equals(PetStatus.PENDING)){
            return ResponseEntity.status(400).build();
        } else {
            List<Pet> collected = petService.getAll().stream().filter(pet -> pet.getStatus().equals(status)).collect(Collectors.toList());
            return ResponseEntity.ok(new ArrayList<>(collected));
        }
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> findById(@PathVariable long petId){
        if(petId<=0){
            return ResponseEntity.status(400).build();
        }
        Optional<Pet> pet = petService.find(petId);
        if(pet.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(pet.get());
    }

    @PostMapping("/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable long petId, @RequestBody String name, @RequestBody String status){
        if (petService.find(petId).isEmpty() || name.isEmpty() || name.isBlank() || status.isEmpty() || status.isBlank()) {
            return ResponseEntity.status(405).build();
        } else {
            Pet pet = petService.find(petId).get();
            pet.setName(name);
            pet.setStatus(status);
            petService.update(pet);
            return ResponseEntity.status(200).build();
        }
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deleteById(@PathVariable long petId){
        Optional<Pet> pet = petService.find(petId);
        if(petId<=0){
            return ResponseEntity.status(400).build();
        }
        if(pet.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        petService.delete(petId);
        return ResponseEntity.status(200).build();
    }
}
