package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "humanResources")
@DynamicUpdate
public class HumanResources {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    @Pattern(regexp = "(\\+((\\d){2,3})|0){1}(\\d){9}$|^NULL$|^$",
            message = "le numero de telephone doit être composé de 10 chiffres ou commencer par l\'indicatif téléphonique (+33 pour la France)")
    private String phoneNumber;
    @Column
    @Email
    private String email;
}
