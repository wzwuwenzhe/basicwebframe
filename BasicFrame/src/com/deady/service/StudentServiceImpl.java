package com.deady.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deady.dao.StudentDAO;
import com.deady.entity.Student;
import com.deady.utils.DateUtils;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public void addStudent(List<Student> studentList) {
		studentDAO.insertStudents(studentList);
	}

	@Override
	public Student getStudentByClassIdAndName(String classId, String name) {
		return studentDAO.findStudentByClassIdAndName(classId, name);
	}

	/**
	 * 
	 * @param studentId
	 * @param type
	 *            1:报名 2:付款
	 */
	@Override
	public void apply(String studentId, String phone, int type) {
		String now = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		if (type == 1) {
			studentDAO.apply(studentId, phone, now);
		} else if (type == 2) {
			studentDAO.pay(studentId, now);
		}
	}

}
