package com.deady.service;

import java.util.List;

import com.deady.entity.Student;

public interface ClassService {

	List<Student> getStudentListByClassId(String classId);

}
