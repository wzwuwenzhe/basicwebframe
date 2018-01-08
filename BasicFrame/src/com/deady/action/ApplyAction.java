package com.deady.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

import com.deady.annotation.DeadyAction;
import com.deady.common.FormResponse;
import com.deady.entity.Operator;
import com.deady.entity.Student;
import com.deady.service.ClassService;
import com.deady.service.StudentService;
import com.deady.utils.DateUtils;
import com.deady.utils.MsgSendUtils;
import com.deady.utils.OperatorSessionInfo;
import com.deady.utils.RedisUtils;

@Controller
public class ApplyAction {

	private static Logger logger = LoggerFactory.getLogger(ApplyAction.class);

	@Autowired
	private ClassService classService;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	@DeadyAction(checkLogin = false, createToken = true)
	public Object viewApply(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String classId = req.getParameter("classId");
		req.setAttribute("classId", classId);
		return new ModelAndView("/apply/apply");
	}

	@RequestMapping(value = "/getStudentByNameAndClassId", method = RequestMethod.POST)
	@DeadyAction(checkLogin = false, createToken = true)
	@ResponseBody
	public Object getStudent(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		FormResponse response = new FormResponse(req);
		String classId = req.getParameter("classId");
		String name = req.getParameter("name");
		Student student = null;
		if (!StringUtils.isEmpty(classId)) {
			student = studentService.getStudentByClassIdAndName(classId, name);
		}
		if (null == student) {
			response.setSuccess(false);
			response.setMessage("抱歉,该班级下没有找到您,请输入正确的姓名后重试!");
			return response;
		}
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value = "/sendCodeMsg", method = RequestMethod.POST)
	@DeadyAction(checkLogin = false, createToken = true)
	@ResponseBody
	public Object sendCodeMsg(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		FormResponse response = new FormResponse(req);
		String classId = req.getParameter("classId");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String pattern = "^0{0,1}(13[0-9]|14[0-9]|15[0-9]|176|177|178|18[0-9])[0-9]{8}$";
		boolean isMatch = Pattern.matches(pattern, phone);
		if (!isMatch) {
			response.setSuccess(false);
			response.setMessage("手机号码不正确!");
			return response;
		}
		Student student = null;
		if (!StringUtils.isEmpty(classId)) {
			student = studentService.getStudentByClassIdAndName(classId, name);
		}
		if (null == student) {
			response.setSuccess(false);
			response.setMessage("抱歉,该班级下没有找到您,请输入正确的姓名后重试!");
			return response;
		}
		if (student.getIsApply().equals("1") && student.getIsPay().equals("0")) {
			response.setSuccess(true);
			response.setMessage("您已成功报名!");
			response.setData("not_pay");
			return response;
		}
		if (student.getIsApply().equals("1") && student.getIsPay().equals("1")) {
			response.setSuccess(true);
			response.setData("is_payed");
			response.setMessage("您已成功报名!并缴费成功!");
			return response;
		}
		// 校验当天发送的验证码的次数
		Jedis jedis = RedisUtils.getJedis();
		String dateStr = DateUtils.getCurrentDate("yyyyMMdd");
		String key = dateStr + "_" + phone;
		String value = jedis.get(dateStr + "_" + phone);
		if (null != value) {
			int times = Integer.parseInt(value);
			if (times >= 3) {
				response.setSuccess(false);
				response.setMessage("抱歉,一天只能发送三次验证码,请明天再试");
				return response;
			} else {
				jedis.set(key, (++times) + "");
			}
		}

		String code = MsgSendUtils.generateCheckCode();
		Map<String, Object> resultMap = MsgSendUtils.sendMsg(phone,
				student.getName(), code);
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		// resultMap.put("result", true);
		logger.info(student.getName() + ":code:" + code);
		if (null != resultMap && (Boolean) resultMap.get("result")) {
			// 验证码 存到session中
			Student stu = OperatorSessionInfo.getStudent(req);
			if (null == stu) {
				stu = student;
			}
			stu.setMsgCode(code);
			stu.setPhone(phone);
			OperatorSessionInfo.save(req,
					OperatorSessionInfo.STUDENT_SESSION_ID, stu, 10);

			// 发成功了一次 记录下来
			if (null == value) {
				jedis.set(key, "1");
			}
			response.setSuccess(true);
			response.setMessage("验证码发送成功!");
			return response;
		} else {
			logger.info("message:" + resultMap.get("message"));
			response.setSuccess(false);
			response.setMessage("验证码发送失败!");
			return response;
		}
	}

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@DeadyAction(checkLogin = false, createToken = true, checkToken = true)
	@ResponseBody
	public Object doApply(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		FormResponse response = new FormResponse(req);
		String vcode = req.getParameter("vcode");
		if (StringUtils.isEmpty(vcode)) {
			response.setSuccess(false);
			response.setMessage("请输入验证码!");
			return response;
		}
		Student stu = OperatorSessionInfo.getStudent(req);
		if (null == stu) {
			response.setSuccess(false);
			response.setMessage("亲,有效期过了,请重试!");
			return response;
		}
		if (!stu.getMsgCode().equals(vcode)) {
			response.setSuccess(false);
			response.setMessage("验证码错误!");
			return response;
		}
		// 验证通过 添加报名信息
		studentService.apply(stu.getId(), stu.getPhone(), 1);
		// 显示付款页面(由于没有注册公司,所以只能显示个人账号二维码)
		response.setSuccess(true);
		response.setMessage("报名成功!");
		return response;
	}

}
