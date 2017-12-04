package com.deady.utils.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.cnblogs.zxub.utils2.http.util.ZRequest;
import com.deady.entity.webinfo.GoodsAnalyserWebInfo;
import com.deady.utils.context.ContextUtil;

public abstract class BasicSpider {

	public GoodsAnalyserWebInfo getPageContent(String url) {
		GoodsAnalyserWebInfo wi = ContextUtil.getGoodsAnalyserWebInfo();
		try {
			String ret = ZRequest
					.Get(url)
					.enableCookie()
					.setCookieStore(
							ContextUtil.getGoodsAnalyserWebInfo()
									.getCookieStore()).execute()
					.returnContent().asString("UTF-8");
			Document doc = Jsoup.parse(ret);
			wi.setSuccess(true);
			wi.setDocument(doc);
			return wi;
		} catch (Exception e) {
			wi.setDocument(null);
			wi.setMessage(e.getMessage());
		}
		return wi;
	}

	public abstract GoodsAnalyserWebInfo doLogin() throws Exception;
}
