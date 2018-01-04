package com.deady.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deady.annotation.DeadyAction;
import com.deady.entity.Student;
import com.deady.service.ClassService;
import com.deady.service.StudentService;
import com.deady.utils.SpringContextUtil;

@Controller
public class ApplyAction {

	@Autowired
	private ClassService classService;
	@Autowired
	private static StudentService studentService;

	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	@DeadyAction(checkLogin = false, createToken = true)
	public Object loginView(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String classId = req.getParameter("classId");
		List<Student> studentList = new ArrayList<Student>();
		if (!StringUtils.isEmpty(classId)) {
			studentList = classService.getStudentListByClassId(classId);
		}
		req.setAttribute("studentList", studentList);
		return new ModelAndView("/apply/apply");
	}

	public static void main(String[] args) {
		Map<String, String> code2NameMap = new TreeMap<String, String>();
		code2NameMap.put("1", "");
		List<Student> studentList = new ArrayList<Student>();
		for (Map.Entry<String, String> entry : code2NameMap.entrySet()) {
			String code = entry.getKey();
			String name = entry.getValue();
			Student student = new Student("577052",
					"a78415a13ad14f09aa204f16029e1eba", UUID.randomUUID()
							.toString().replaceAll("-", ""), name, "", code);
			studentList.add(student);
		}
		StudentService _studentService = (StudentService) SpringContextUtil
				.getBeanByClass(StudentService.class);
		_studentService.addStudent(studentList);

	}
}
