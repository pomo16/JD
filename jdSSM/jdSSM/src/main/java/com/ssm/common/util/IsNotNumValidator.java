package com.ssm.common.util;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsNotNumValidator implements ConstraintValidator<IsNotNum, String> {
	 @Override
	 public void initialize(IsNotNum isNotNum) {
	 }
	 
	 @Override
	 public boolean isValid(String str, ConstraintValidatorContext ctx) {
		 Pattern pattern = Pattern.compile("^\\d+$");
		 return !pattern.matcher(str).matches();
    }
}
