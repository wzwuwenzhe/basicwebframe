package com.deady.dao;

import java.util.List;

import com.deady.entity.Clazz;
import com.deady.entity.Student;

public interface ClassDAO {

	List<Student> findStudentListByClassId(String classId);

	List<Clazz> findClassListBySchoolIds(List<String> schoolIdList);

}
