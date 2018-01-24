package com.upotv.mcs.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

	private Email email;

	@Override
	public void initialize(Email email) {
		this.email = email;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
		boolean isValid;
		
		if(value != null){
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			isValid = Pattern.compile(check).matcher(value).matches();
		}else{
			isValid = false;
		}
		
		if (!isValid) {
			constraintContext.disableDefaultConstraintViolation();
			constraintContext.buildConstraintViolationWithTemplate(email.message()).addConstraintViolation();
		}
		return isValid;
	}

}
