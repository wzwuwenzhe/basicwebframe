package com.deady.service;

import java.util.Date;
import java.util.List;

import com.deady.entity.Student;

public interface StudentService {

	void addStudent(List<Student> studentList);

	Student getStudentByClassIdAndName(String classId, String name);

	/**
	 * 
	 * @param studentId
	 * @param type
	 *            1:报名 2:付款
	 */
	void apply(String studentId, String phone, int type);

}
