package com.tx.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tx.enums.dao.DBTypeEnum;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	DBTypeEnum value();
}