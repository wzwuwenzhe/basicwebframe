package com.deady.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(LogInterceptor.class);

	public void before(JoinPoint joinpoint) {
		Object[] objArr = joinpoint.getArgs();// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		for (Object object : objArr) {
			if (object instanceof HttpServletRequest) {
				// HttpServletRequest req = (HttpServletRequest) object;
				// Operator operator = OperatorSessionInfo.getOperator(req);
				// if (null != operator) {
				// logger.info("操作员ID:" + operator.getId() + " 姓名:"
				// + operator.getName() + " 店铺ID:"
				// + operator.getStoreId() + " requestURL:"
				// + req.getRequestURL());
				// Enumeration em = req.getParameterNames();
				// while (em.hasMoreElements()) {
				// String name = (String) em.nextElement();
				// String value = req.getParameter(name);
				// logger.info(name + " : " + value);
				// }
				//
				// }
			}
		}
	}

	public void after(JoinPoint joinpoint) {

	}
}
