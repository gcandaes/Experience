package com.example.retourexperience.shared;

import com.example.retourexperience.ui.model.entity.Work;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<CheckDates, Work> {

    @Override
    public boolean isValid(Work work, ConstraintValidatorContext context) {
        return work.getEndDate().after(work.getStartDate());
    }
}
