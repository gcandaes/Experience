package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    @Size(min = 2, max = 25, message = "le nom de l'établissement doit comporter au moins 2 caractères")
    @NotNull
    private String name;
    @Column(nullable = false)
    @Size(min = 5, max = 5, message = "le code postal doit être composé de 5 chiffres")
    @NotNull
    private String zipCode;
    @Column(nullable = false)
    @NotNull
    private String city;
}
