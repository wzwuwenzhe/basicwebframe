package com.deady.entity;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = -4985803759679763488L;
	@BasicEntityField(length = 6)
	private String schoolId;// 学校id
	@BasicEntityField(length = 32)
	private String classId;// 班级id
	@BasicEntityField(length = 32)
	private String id;// 学生id
	@BasicEntityField(length = 50)
	private String name;// 姓名
	@BasicEntityField(length = 13)
	private String phone;// 电话
	@BasicEntityField(length = 10)
	private String code;// 学号
	@BasicEntityField(length = 1)
	private String isApply;// 是否报名
	private String applyTime;// 报名时间
	@BasicEntityField(length = 1)
	private String isPay;// 是否支付
	private String payTime;// 支付时间
	private String creationTime;// 创建时间

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
}