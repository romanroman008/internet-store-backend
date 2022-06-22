package pl.meating.meatingback.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TotalNumberValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface TotalNumber {
    String message()default "Number is not total wariacie";
    Class<?>[]groups()default{};
    Class<?extends Payload>[]payload()default{};

}
