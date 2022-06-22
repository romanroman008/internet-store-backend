package pl.meating.meatingback.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TotalNumberValidator implements ConstraintValidator<TotalNumber,Float> {
    @Override
    public void initialize(TotalNumber constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext constraintValidatorContext) {
        if(value%1==0)
            return true;
        return false;
    }


}
