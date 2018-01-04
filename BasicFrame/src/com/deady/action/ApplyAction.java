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
		code2NameMap.put("1", "陈亨特");
		code2NameMap.put("2", "陈杨");
		code2NameMap.put("3", "陈怡");
		code2NameMap.put("4", "陈茜茹");
		code2NameMap.put("5", "戴亨名");
		code2NameMap.put("6", "戴若婷");
		code2NameMap.put("7", "董衍俊");
		code2NameMap.put("8", "方垟泽");
		code2NameMap.put("9", "洪顺");
		code2NameMap.put("10", "黄亚赛");
		code2NameMap.put("11", "黄迎然");
		code2NameMap.put("12", "贾冕");
		code2NameMap.put("13", "金恩丽");
		code2NameMap.put("14", "金佳乐");
		code2NameMap.put("15", "金仙炜");
		code2NameMap.put("16", "兰菁菁");
		code2NameMap.put("17", "李东凡");
		code2NameMap.put("18", "李冬冬");
		code2NameMap.put("19", "李卓人");
		code2NameMap.put("20", "林博");
		code2NameMap.put("21", "林建聪");
		code2NameMap.put("22", "林洁琛");
		code2NameMap.put("23", "滕银涨");
		code2NameMap.put("24", "滕朱快特");
		code2NameMap.put("25", "王琳");
		code2NameMap.put("26", "王梦梦");
		code2NameMap.put("27", "王明东");
		code2NameMap.put("28", "王昭");
		code2NameMap.put("29", "吴丽凡");
		code2NameMap.put("30", "吴文哲");
		code2NameMap.put("31", "夏坚");
		code2NameMap.put("32", "项柱中");
		code2NameMap.put("33", "肖武敏");
		code2NameMap.put("34", "谢硕超");
		code2NameMap.put("35", "徐佳迎");
		code2NameMap.put("36", "徐俊贤");
		code2NameMap.put("37", "杨孙捷");
		code2NameMap.put("38", "余瑞杰");
		code2NameMap.put("39", "詹婵婵");
		code2NameMap.put("40", "詹杰");
		code2NameMap.put("41", "张凯");
		code2NameMap.put("42", "张桥");
		code2NameMap.put("43", "张天行");
		code2NameMap.put("44", "郑朝升");
		code2NameMap.put("45", "郑龙纯");
		code2NameMap.put("46", "郑炜");
		code2NameMap.put("47", "周奕彬");
		code2NameMap.put("48", "周莹莹");
		code2NameMap.put("49", "林翀");
		
		List<Student> studentList = new ArrayList<Student>();
		for (Map.Entry<String, String> entry : code2NameMap.entrySet()) {
			String code = entry.getKey();
			String name = entry.getValue();
			Student student = new Student("577052",
					"a78415a13ad14f09aa204f16029e1eba", UUID.randomUUID()
							.toString().replaceAll("-", ""), name, "", code);
			studentList.add(student);
		}
		System.out.println(code2NameMap);
//		StudentService _studentService = (StudentService) SpringContextUtil
//				.getBeanByClass(StudentService.class);
//		_studentService.addStudent(studentList);

	}
}
