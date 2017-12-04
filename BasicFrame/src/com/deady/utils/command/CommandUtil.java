package com.deady.utils.command;

import javax.servlet.http.HttpServletRequest;

import net.shunpay.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deady.command.chain.PlugCommandChain;
import com.deady.entity.webinfo.GoodsAnalyserWebInfo;
import com.deady.utils.context.ContextUtil;

public class CommandUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(CommandUtil.class);

	private static ThreadLocal<Boolean> lock = new ThreadLocal<Boolean>();

	public static void setLock(boolean flag) {
		lock.set(flag);
	}

	public static boolean isLock() {
		if (lock.get() == null) {
			return false;
		}
		return lock.get();
	}

	public static GoodsAnalyserWebInfo execute(HttpServletRequest request,
			PlugCommandChain pcc) throws Exception {
		pcc.execute(ContextUtil.getContext());
		GoodsAnalyserWebInfo wi = ContextUtil.getGoodsAnalyserWebInfo();

		if (CommandUtil.isLock()) {
			// CounterUtil.finishJob(ContextUtil.getGid());
		}

		ContextUtil.clear();
		lock.remove();

		try {
			logger.info(JsonUtil.toJson(wi));
		} catch (Exception e) {
			logger.warn("返回对象显示出错", e);
		}
		return wi;
	}

}
