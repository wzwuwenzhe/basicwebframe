package com.deady.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.deady.entity.Student;

public interface StudentDAO {

	void insertStudents(List<Student> studentList);

	Student findStudentByClassIdAndName(@Param("classId") String classId,
			@Param("name") String name);

	void apply(@Param("studentId") String studentId,
			@Param("phone") String phone, @Param("now") String now,
			@Param("applyType") String applyType);

	void pay(@Param("studentId") String studentId, @Param("now") String now);

	Student findStudentById(String studentId);

}
