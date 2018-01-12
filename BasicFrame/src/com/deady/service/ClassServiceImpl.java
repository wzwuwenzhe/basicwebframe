package com.deady.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deady.dao.ClassDAO;
import com.deady.entity.Clazz;
import com.deady.entity.Student;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDAO classDAO;

	@Override
	public List<Student> getStudentListByClassId(String classId) {
		return classDAO.findStudentListByClassId(classId);
	}

	@Override
	public List<Clazz> getClassListBySchoolIds(List<String> schoolIdList) {
		return classDAO.findClassListBySchoolIds(schoolIdList);
	}

}
