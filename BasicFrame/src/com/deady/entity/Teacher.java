package com.deady.entity;

import java.io.Serializable;

public class Teacher implements Serializable {
	private static final long serialVersionUID = 740171007267114524L;
	@BasicEntityField(length = 6)
	private String schoolId;// 学校Id
	@BasicEntityField(length = 32)
	private String id;// 教师id
	@BasicEntityField(length = 50)
	private String name;// 姓名
	@BasicEntityField(length = 13)
	private String phone;// 电话
	@BasicEntityField(length = 1)
	private String isApply;// 是否报名
	private String applyTime;// 报名时间
	private String creationTime;// 创建时间

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsApply() {
		return isApply;
	}

	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
}
