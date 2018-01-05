package com.deady.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deady.dao.StudentDAO;
import com.deady.entity.Student;

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

}
