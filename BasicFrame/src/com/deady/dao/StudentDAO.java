package com.deady.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.deady.entity.Student;

public interface StudentDAO {

	void insertStudents(List<Student> studentList);

	Student findStudentByClassIdAndName(@Param("classId") String classId,
			@Param("name") String name);

}
