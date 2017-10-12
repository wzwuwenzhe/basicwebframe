package com.deady.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deady.annotation.DeadyAction;

@Controller
public class BasicFrameTestAction {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@DeadyAction(createToken = true)
	public Object billing(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		return new ModelAndView("/test/test");
	}
}
