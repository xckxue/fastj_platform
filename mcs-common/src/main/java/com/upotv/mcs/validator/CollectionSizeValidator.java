package com.upotv.mcs.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.regex.Pattern;

public class CollectionSizeValidator implements ConstraintValidator<CollectionSize, Collection> {

	private CollectionSize collectionSize;

	@Override
	public void initialize(CollectionSize collectionSize) {
		this.collectionSize = collectionSize;
	}

	@Override
	public boolean isValid(Collection value, ConstraintValidatorContext constraintContext) {
		boolean isValid;

		int min = collectionSize.min();

		if(value == null || value.size() < min){
			isValid = false;
		}else{
			isValid = true;
		}
		
		if (!isValid) {
			constraintContext.disableDefaultConstraintViolation();
			constraintContext.buildConstraintViolationWithTemplate(collectionSize.message()).addConstraintViolation();
		}
		return isValid;
	}

}
