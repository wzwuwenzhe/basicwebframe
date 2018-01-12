package com.deady.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deady.annotation.DeadyAction;
import com.deady.common.FormResponse;
import com.deady.entity.Operator;
import com.deady.service.OperatorService;
import com.deady.utils.MD5Utils;
import com.deady.utils.OperatorSessionInfo;

@Controller
public class LoginAction {

	@Autowired
	private OperatorService operatorService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@DeadyAction(checkLogin = false, createToken = true)
	public Object loginView(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Cookie[] cookies = req.getCookies();
		if (null == cookies) {
			return new ModelAndView("/login/login");
		}
		boolean flag = false;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(OperatorSessionInfo.COOKIE_USER_NAME)) {
				req.setAttribute("username", cookie.getValue());
				continue;
			}
			if (cookie.getName().equals(OperatorSessionInfo.COOKIE_USER_PWD)) {
				req.setAttribute("password", cookie.getValue());
				flag = true;
				continue;
			}
		}
		if (flag) {
			req.setAttribute("remember", "1");
		}
		return new ModelAndView("/login/login");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@DeadyAction(checkLogin = false)
	public Object redirectLogin(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Operator op = (Operator) OperatorSessionInfo.getOperator(req);
		if (!StringUtils.isEmpty(op)) {
			return new ModelAndView("redirect:/reserveSearch");
		}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@DeadyAction(checkLogin = false, checkToken = true, createToken = true)
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String userName = req.getParameter("username");
		String pwd = req.getParameter("password");
		FormResponse response = new FormResponse(req);
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(pwd)) {
			response.setSuccess(false);
			response.setMessage("用户名密码不能为空");
			return response;
		}
		Operator op = operatorService.getOperatorByLoginName(userName);
		if (StringUtils.isEmpty(op)
				|| !op.getPwd().toUpperCase().equals(pwd.toUpperCase())) {
			response.setSuccess(false);
			response.setMessage("用户名或密码错误");
			return response;
		}
		// 把用户信息存到Session中24小时
		OperatorSessionInfo.save(req, OperatorSessionInfo.OPERATOR_SESSION_ID,
				op, 24 * 60);
		// 如果选择记住就将用户名和密码存到Cookie中否则就删掉
		OperatorSessionInfo.saveCookie(req, res);
		response.setSuccess(true);
		return response;
	}

}
