package com.example.api.member.dto;

import java.util.HashMap;
import java.util.Map;

public enum MemberRoleEnum {
	ADMIN("9999"),
	MEMBER("10000"),
	ANONYMOUS("10003");

	private String roleCode;

	MemberRoleEnum(String roleCode) {
		this.roleCode = roleCode;
	}

	public static Map<String, Object> getAdminMap() {
		Map<String, Object> admin = new HashMap<>();

		admin.put("jaeryeol@3top.co.kr", ADMIN.getValue());
		admin.put("admin", ADMIN.getValue());

		return admin;
	}

	public String getValue() {
		return this.roleCode;
	}
}
