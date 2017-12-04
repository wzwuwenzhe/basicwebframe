package com.deady.utils.context;

import com.deady.entity.webinfo.GoodsAnalyserWebInfo;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;

public class ContextUtil {

	// 单例
	private static ThreadLocal<GoodsAnalyserWebInfo> webInfo = new ThreadLocal<GoodsAnalyserWebInfo>();
	private static ThreadLocal<Context> context = new ThreadLocal<Context>();

	public static Context getContext() {
		if (context.get() == null) {
			context.set(new ContextBase());
		}
		return context.get();
	}

	public static GoodsAnalyserWebInfo getGoodsAnalyserWebInfo() {
		if (webInfo.get() == null) {
			webInfo.set(new GoodsAnalyserWebInfo());
		}
		return webInfo.get();
	}

	public static void clear() {
		webInfo.remove();
		context.remove();
	}
}
