package com.ssm.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { PasswordRuleValidator.class })
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordRule {
     
    String message() default "有被盗风险，建议使用字母、数字和符号两种及以上组合";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
 
}
