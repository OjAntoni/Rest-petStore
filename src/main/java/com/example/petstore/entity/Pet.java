package com.example.petstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
    @NotEmpty
    @NotNull
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;
    @NotEmpty
    @NotNull
    private String status;

    public Pet(Pet newPet){
        if(newPet.getCategory()!=null)
            this.category = newPet.getCategory();
        if(newPet.getName()!=null)
            this.name = newPet.getName();
        if(newPet.getTags()!=null)
            this.tags = newPet.getTags();
        if(newPet.getStatus()!=null)
            this.status = newPet.getStatus();
    }
}
