package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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
@Table(name = "experience")
@DynamicUpdate
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(cascade=CascadeType.ALL)
    @Valid
    private Place place;

    @OneToOne(cascade=CascadeType.ALL)
    @Valid
    private Employer employer;

    @OneToOne(cascade=CascadeType.ALL)
    @Valid
    private Work work;

/*    @OneToOne
    private Conditions conditions;

    @OneToOne
    private Practical practical;*/


}
