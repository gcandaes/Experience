package com.example.retourexperience.ui.model.requestDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDtoModel {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String city;
}
