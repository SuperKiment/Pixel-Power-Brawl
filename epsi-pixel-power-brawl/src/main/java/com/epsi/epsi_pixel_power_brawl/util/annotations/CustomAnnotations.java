package com.epsi.epsi_pixel_power_brawl.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.epsi.epsi_pixel_power_brawl.util.validators.PasswordMatchesValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;


public class CustomAnnotations {

	@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = PasswordMatchesValidator.class)
	@Documented
	public @interface PasswordMatches {
	    String message() default "Passwords don't match";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	}

}
