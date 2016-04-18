package com.qiang.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.qiang.anno.DataSourceEnum;
import com.qiang.anno.DateSource;
import com.qiang.datasource.DataSourceSwitcher;

@Aspect
public class DataSourceAdvice {

	/**
	 * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的
	 */
	public static final String EDP = "execution(* com.qiang.dao.*.*(..))";

	@After(EDP)
	public void afterReturning() {
		DataSourceSwitcher.setSlave();
	}

	@Before(EDP)
	public void before(JoinPoint joinPoint) {
		System.out.println("=================");
		String methodNm = joinPoint.getSignature().getName();
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodNm)) {
				DateSource d = method.getAnnotation(DateSource.class);
				if (d != null) {
					DataSourceEnum value = d.value();
					if (value.equals(DataSourceEnum.MASTER)) {
						DataSourceSwitcher.setMaster();
					} else {
						DataSourceSwitcher.setSlave();
					}
				}
				break;
			}
		}

	}
}
