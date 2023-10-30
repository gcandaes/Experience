package com.example.retourexperience.ui.model.requestDto;

import com.example.retourexperience.ui.model.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto {
    @NotNull(message = "place can not be null")
    private Place place;
    @NotNull(message = "employer can not be null")
    private Employer employer;
    @NotNull(message = "work can not be null")
    private Work work;
    @NotNull(message = "conditions can not be null")
    private Conditions conditions;
    @NotNull(message = "firstname can not be null")
    private Practical practical;
}
