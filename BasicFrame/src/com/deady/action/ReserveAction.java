package com.deady.action;

import java.util.ArrayList;
import java.util.List;

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
import com.deady.entity.Clazz;
import com.deady.entity.Operator;
import com.deady.entity.Student;
import com.deady.enums.UserTypeEnum;
import com.deady.service.ClassService;
import com.deady.service.SchoolService;
import com.deady.service.StudentService;
import com.deady.utils.OperatorSessionInfo;

@Controller
public class ReserveAction {

	@Autowired
	private ClassService classService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/reserveSearch", method = RequestMethod.GET)
	@DeadyAction(checkLogin = true, createToken = true)
	public Object viewReserve(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Operator op = OperatorSessionInfo.getOperator(req);
		List<String> schoolIdList = new ArrayList<String>();
		if (op.getUserType().equals(UserTypeEnum.ADMIN.getType())) {
			schoolIdList = schoolService.getAllSchoolIds();
		} else {
			schoolIdList.add(op.getSchoolId());
		}
		List<Clazz> classList = classService
				.getClassListBySchoolIds(schoolIdList);
		req.setAttribute("operator", op);
		req.setAttribute("classList", classList);
		return new ModelAndView("/reserve/reserve");
	}

	@RequestMapping(value = "/reserveSearch", method = RequestMethod.POST)
	@DeadyAction(checkLogin = true, createToken = true)
	public Object doReserveSearch(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Operator op = OperatorSessionInfo.getOperator(req);
		List<String> schoolIdList = new ArrayList<String>();
		if (op.getUserType().equals(UserTypeEnum.ADMIN.getType())) {
			schoolIdList = schoolService.getAllSchoolIds();
		} else {
			schoolIdList.add(op.getSchoolId());
		}
		List<Clazz> classList = classService
				.getClassListBySchoolIds(schoolIdList);
		String classId = req.getParameter("classId");
		String schoolId = req.getParameter("schoolId");
		List<Student> studentList = new ArrayList<Student>();
		if (!StringUtils.isEmpty(classId)) {
			studentList = classService.getStudentListByClassId(classId);
		}
		req.setAttribute("operator", op);
		req.setAttribute("classList", classList);
		req.setAttribute("studentList", studentList);
		req.setAttribute("schoolId", schoolId);
		req.setAttribute("classId", classId);
		return new ModelAndView("/reserve/reserve");
	}

	@RequestMapping(value = "/payed", method = RequestMethod.POST)
	@DeadyAction(checkLogin = true, createToken = true, checkToken = true)
	@ResponseBody
	public Object isPayed(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		FormResponse response = new FormResponse(req);
		String studentId = req.getParameter("studentId");
		Student student = studentService.getStudentById(studentId);
		if (null == student) {
			response.setSuccess(false);
			response.setMessage("参数错误!");
			return response;
		}
		studentService.apply(student.getId(), student.getPhone(), 2);
		response.setSuccess(true);
		response.setMessage("支付标志修改成功");
		return response;
	}
}
