package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "humanResources")
public class HumanResources {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    @Size(min = 2, max = 25, message = "le prénom doit comporter au moins 2 caractères")
    private String firstName;
    @Column(nullable = false)
    @Size(min = 2, max = 25, message = "le nom doit comporter au moins 2 caractères")
    private String lastName;
    @Column
    @Pattern(regexp = "(\\+((\\d){2,3})|0){1}(\\d){9}$|^NULL$|^$",
            message = "le numero de telephone doit être composé de 10 chiffres ou commencer par l\'indicatif téléphonique (+33 pour la France)")
    private String phoneNumber;
    @Column
    @Email
    private String email;
}
