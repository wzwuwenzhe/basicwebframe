package com.deady.entity;

import java.io.Serializable;

public class School implements Serializable {
	private static final long serialVersionUID = -6250769526950902565L;
	@BasicEntityField(length = 6)
	private String id; // 学校编号
	@BasicEntityField(length = 50)
	private String name; // 姓名
	@BasicEntityField(length = 100)
	private String admin;// 管理员账号的集合
	private String creationTime;// 创建时间

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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

}
