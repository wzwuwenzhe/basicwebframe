package com.deady.mvc.spider;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnblogs.zxub.utils2.configuration.ConfigUtil;
import com.cnblogs.zxub.utils2.http.util.Response;
import com.cnblogs.zxub.utils2.http.util.ZRequest;
import com.deady.entity.webinfo.GoodsAnalyserWebInfo;
import com.deady.utils.context.ContextUtil;
import com.deady.utils.spider.BasicSpider;
import com.deady.utils.vcode.SimpleVCodeUtils;

public class GoodsAnalyseSpider extends BasicSpider {

	private static Logger logger = LoggerFactory
			.getLogger(GoodsAnalyseSpider.class);

	private static final PropertiesConfiguration config = ConfigUtil
			.getProperties("goodsAnalyser");

	@SuppressWarnings("static-access")
	@Override
	public GoodsAnalyserWebInfo doLogin() throws Exception {
		GoodsAnalyserWebInfo webInfo = ContextUtil.getGoodsAnalyserWebInfo();
		String loginUrl = config.getString("login.url");
		String vCodeUrl = config.getString("vcode.url");
		String captchaVal = "";// 验证码结果
		CookieStore cookieStore = new BasicCookieStore();
		SimpleVCodeUtils utils = new SimpleVCodeUtils(cookieStore);
		Map<String, String> params = new HashMap<String, String>();
		try {
			this.getUri(loginUrl);
			// 是否无需重新登录
			// 丢失登录状态 则重新登陆
			// 获取到_token
			String back = ZRequest.Get(loginUrl).enableCookie()
					.setCookieStore(cookieStore).execute().returnContent()
					.asString();

			Document doc = Jsoup.parse(back);
			String token = doc.select("[name=_token]").get(0).val();
			// 获取验证码
			String vcode = utils.getVCodeByUrl(vCodeUrl,
					"C:\\Users\\wzwuw\\git\\basicframe\\BasicFrame\\img\\",
					"temp");

			logger.info(vcode);
			// 参数组装
			params.put("vcode", vcode);
			params.put("agentCode", config.getString("agent.phone"));
			params.put("agentPwd", config.getString("agent.login.pwd"));
			params.put("_token", token);
			// 发起登录请求
			String ret = ZRequest
					.Post(loginUrl)
					.setReferer(loginUrl)
					.addHeader("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.enableCookie().setCookieStore(cookieStore)
					.send("UTF-8", params).execute().returnContent().asString();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return webInfo;
	}

	private String getUri(String uri) throws Exception {
		return ZRequest
				.Get(uri)
				.enableCookie()
				.setCookieStore(
						ContextUtil.getGoodsAnalyserWebInfo().getCookieStore())
				.execute().returnContent().asString("UTF-8");
	}
}
