package com.deady.entity;

import java.io.Serializable;

public class Clazz implements Serializable {
	private static final long serialVersionUID = 1820718651422258972L;
	@BasicEntityField(length = 6)
	private String schoolId;// 学校Id
	@BasicEntityField(length = 32)
	private String id;// 班级 编号
	@BasicEntityField(length = 50)
	private String name;// 姓名
	@BasicEntityField(length = 1000)
	private String teacherIds;// 教师id集合
	private String creation_time;// 创建时间

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
}
