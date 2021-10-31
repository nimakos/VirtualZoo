package gr.nikolis.handlers.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnimalNameValidator implements ConstraintValidator<AnimalName, String> {
    @Override
    public void initialize(AnimalName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String animalNameField, ConstraintValidatorContext context) {
        return animalNameField != null && animalNameField.length() > 3 && animalNameField.length() < 10;
    }
}
