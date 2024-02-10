package com.example.retourexperience.ui.model.entity;

import jakarta.persistence.*;
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
@Table(name = "practical")
@DynamicUpdate
public class Practical {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
