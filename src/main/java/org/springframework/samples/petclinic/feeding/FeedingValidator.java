package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FeedingValidator implements Validator {

	private static final String REQUIRED = "required";

	@Override
	public boolean supports(Class<?> clazz) {
		return Feeding.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Feeding feeding = (Feeding) target;
        LocalDate startDate = feeding.getStartDate();

        if(startDate == null){
            errors.rejectValue("startDate", REQUIRED, REQUIRED);
        }

        if(feeding.isNew() && feeding.getPet()==null){
            errors.rejectValue("pet", REQUIRED, REQUIRED);
        }
		
	}
    
}
