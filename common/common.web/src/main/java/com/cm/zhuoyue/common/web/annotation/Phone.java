package com.cm.zhuoyue.common.web.annotation;

import com.cm.zhuoyue.common.web.RegexpConstants;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * @author 陈萌
 * @date 2021/6/22
 */
@ConstraintComposition(CompositionType.AND)
@Pattern(regexp = RegexpConstants.REGEXP_TELEPHONE_MOBILE)
@Length(min = 0, max = 11)
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {

    String message() default "手机号校验错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
