package com.epsi.epsi_pixel_power_brawl.util.validators;

import com.epsi.epsi_pixel_power_brawl.dto.RegisterDto;
import com.epsi.epsi_pixel_power_brawl.util.annotations.CustomAnnotations.PasswordMatches;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
	  implements ConstraintValidator<PasswordMatches, Object> {
	    
	    @Override
	    public void initialize(PasswordMatches constraintAnnotation) {
	    }
	    
	    @Override
	    public boolean isValid(Object obj, ConstraintValidatorContext context){
	        RegisterDto user = (RegisterDto) obj;
	        return user.getPassword().equals(user.getMatchingPassword());
	    }
}
