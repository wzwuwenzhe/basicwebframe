package com.deady.service;

import java.util.List;

import com.deady.entity.Student;

public interface StudentService {

	void addStudent(List<Student> studentList);

	Student getStudentByClassIdAndName(String classId, String name);

}
