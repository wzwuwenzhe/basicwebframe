package com.deady.entity.webinfo;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.jsoup.nodes.Document;

import com.cnblogs.zxub.utils2.configuration.ConfigUtil;

public class GoodsAnalyserWebInfo implements Serializable {

	private static final long serialVersionUID = -8344168439593064875L;

	private String agentPhone;// 代理商登录账号
	private String password;// 代理商登录密码
	private String vrcode;// 验证码
	private CookieStore cookieStore = new BasicCookieStore();
	private HttpSession session = null;
	private static final PropertiesConfiguration config = ConfigUtil
			.getProperties("goodsAnalyser");
	private boolean success = false;
	private Document document = null;
	private String message = "";

	public GoodsAnalyserWebInfo() {
		this.agentPhone = getAgentPhone();
		this.password = getPassword();
	}

	public String getAgentPhone() {
		return config.getString("agent.phone", "13570036222");
	}

	public String getPassword() {

		return config.getString("agent.login.pwd", "22222222");
	}

	public String getVrcode() {
		return vrcode;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public void setVrcode(String vrcode) {
		this.vrcode = vrcode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
