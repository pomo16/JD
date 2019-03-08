package com.ssm.common.util;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordRuleValidator implements ConstraintValidator<PasswordRule, String> {
	 @Override
	 public void initialize(PasswordRule passwordRule) {
	 }
	 
	 @Override
	 public boolean isValid(String str, ConstraintValidatorContext ctx) {
		 Pattern pattern = Pattern.compile("^([0-9]+|[a-zA-Z]+|[^0-9a-zA-Z]+)$");
		 return !pattern.matcher(str).matches();
   }
}
