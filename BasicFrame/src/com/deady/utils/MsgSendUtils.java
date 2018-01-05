package com.deady.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cnblogs.zxub.utils2.configuration.ConfigUtil;

public class MsgSendUtils {

	private static final PropertiesConfiguration config = ConfigUtil
			.getProperties("deady");

	public static Map<String, Object> sendMsg(String phone, String name,
			String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		String connectTimeout = config.getString("connect.time.out");
		String readTimeout = config.getString("read.time.out");
		// 替换成你的AK
		final String accessKeyId = config.getString("access.key.id");
		final String accessKeySecret = config.getString("access.key.secret");
		String signName = "吴先森";
		String templateId = config.getString("template.id");
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout",
				connectTimeout);
		System.setProperty("sun.net.client.defaultReadTimeout", readTimeout);

		// 初始化ascClient需要的几个参数
		final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
					domain);
		} catch (ClientException e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("message", e.getMessage());
			return result;
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateId);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"name\":\"" + name + "\", \"code\":\""
				+ code + "\"}");
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("message", e.getMessage());
			return result;
		} catch (ClientException e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("message", e.getMessage());
			return result;
		}
		if (sendSmsResponse.getCode() != null
				&& sendSmsResponse.getCode().equals("OK")) {
			result.put("result", true);
			result.put("message", "请求成功!");
			return result;
		} else {
			result.put("result", false);
			result.put("message", sendSmsResponse.getCode() + ":"
					+ sendSmsResponse.getMessage());
			return result;
		}
	}

	public static String generateCheckCode() {
		String chars = "0123456789";
		char[] rands = new char[6];
		for (int i = 0; i < 6; i++) {
			int rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}
		return rands.toString();
	}

}
