package com.example.retourexperience.ui.model.entity;

import com.example.retourexperience.shared.CheckDates;
import com.example.retourexperience.ui.model.enumeration.FunctionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "work")
@CheckDates
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = FunctionEnum.class)
    private Set<FunctionEnum> occupiedFunction;
    @Column(nullable = false, length = 50)
    private String missions;
    @Column(nullable = false)
    private String experienceBefore;//votre experience pour le poste au moment de l'embauche
    @Column(columnDefinition = "DATE")
    private Date startDate;
    @Column(columnDefinition = "DATE")
    private Date endDate;
}
