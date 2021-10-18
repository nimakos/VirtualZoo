package gr.nikolis.handlers.validations.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    Class<?>[] groups() default {}; //need it by the framework

    Class<? extends Payload>[] payload() default {}; //need it by the framework

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
