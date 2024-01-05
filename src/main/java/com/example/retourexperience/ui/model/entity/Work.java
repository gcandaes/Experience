package com.example.retourexperience.ui.model.entity;

import com.example.retourexperience.shared.CheckDates;
import com.example.retourexperience.ui.model.enumeration.FunctionEnumBis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "work")
@CheckDates
@DynamicUpdate
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, length = 500)
    private ArrayList<FunctionEnumBis> occupiedFunctions;

    @Column(nullable = false, length = 50)
    private String missions;
    @Column(nullable = false)
    private String experienceBefore;//votre experience pour le poste au moment de l'embauche
    @Column(columnDefinition = "DATE")
    private Date startDate;
    @Column(columnDefinition = "DATE")
    private Date endDate;
}
