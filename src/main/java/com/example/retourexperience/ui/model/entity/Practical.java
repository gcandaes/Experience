package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "practical")
public class Practical {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    /*    @ElementCollection
/*    @CollectionTable(name = "furniture", joinColumns = @JoinColumn(name = "practical_id"))
       private Set<FurnitureEnum> furniture = new HashSet<>();*/
    private String accompanyingPerson;//number and nature


}
