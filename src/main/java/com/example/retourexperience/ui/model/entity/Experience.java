package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @Valid
    private Place place;

    @OneToOne
    @Valid
    private Employer employer;

    @OneToOne
    @Valid
    private Work work;

/*    @OneToOne
    private Conditions conditions;

    @OneToOne
    private Practical practical;*/


}
