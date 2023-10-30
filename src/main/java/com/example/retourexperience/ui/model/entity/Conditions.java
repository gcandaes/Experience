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
@Table(name = "conditions")
public class Conditions {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)

   private String id;
 /*    @Column(nullable = false)
    private boolean accomodation;
    private boolean furnished;
    private int roomsNumber;
    private int occupantNb;
    private boolean kitchen;
    private boolean bathroom;

    @Column(nullable = false)
    private boolean meals;*/


}
