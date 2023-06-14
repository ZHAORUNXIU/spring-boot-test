package com.x.test.common.annotation;

import java.lang.annotation.*;

/**
 * @author runxiu.zhao
 * 创建字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD})
public @interface CreatedAt {

}
