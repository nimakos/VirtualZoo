package gr.nikolis.handlers.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AnimalNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnimalNameQualifier {
    String message() default "Invalid animal name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
