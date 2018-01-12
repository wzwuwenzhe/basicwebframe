package com.deady.enums;

public enum ReserveStateEnum {

	NOT_REGISTER("0", "未登记"), NOT_GO("1", "不参加"), NOT_PAY("2", "已报名,未缴费"), PAYED(
			"3", "已缴费"), UNKNOW("4", "未知状态");

	private String type;
	private String desc;

	ReserveStateEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

}
