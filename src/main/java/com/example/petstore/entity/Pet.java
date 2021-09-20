package com.example.petstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private long id;
    private Category category;
    @NotEmpty
    @NotNull
    private String name;
    private List<Tag> tags;
    @NotEmpty
    @NotNull
    private String status;

    public Pet(Pet newPet){
        this.category = newPet.getCategory();
        this.name = newPet.getName();
        this.tags = newPet.getTags();
        this.status = newPet.getStatus();
    }
}
