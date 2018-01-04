package com.deady.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.deady.dao.StudentDAO;
import com.deady.entity.Student;

public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public void addStudent(List<Student> studentList) {
		studentDAO.insertStudents(studentList);
	}

}
