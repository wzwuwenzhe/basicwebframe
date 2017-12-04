package com.deady.command.base;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import com.deady.entity.webinfo.GoodsAnalyserWebInfo;
import com.deady.mvc.spider.GoodsAnalyseSpider;

public class GetGoodsInfo implements Command {

	@Override
	public boolean execute(Context arg0) throws Exception {
		GoodsAnalyseSpider spider = new GoodsAnalyseSpider();
		try {
			spider.doLogin();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
