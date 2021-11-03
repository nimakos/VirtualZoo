package gr.nikolis.handlers.validations.customValidator1;

import gr.nikolis.sql.dao.Animal;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    /**
     * Do the logic
     *
     * @param value   The object parameter
     * @param context Context in which the constraint is evaluated
     * @return True if validations passed
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Animal animal = (Animal) value;
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        //todo : make validations
        /*if (fieldValue != null)
            return fieldValue.equals(fieldMatchValue);
        else
            return fieldMatchValue == null;*/
        return true;
    }
}
