package com.deady.command.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deady.command.base.GetGoodsInfo;

public class GetGoodsInfoChain extends PlugCommandChain {

	private static Logger logger = LoggerFactory
			.getLogger(GetGoodsInfoChain.class);

	@Override
	public void setChainCommands() {
		// logger.info("充值中, 传入参数：{}", RequestContextUtil.getContext());
		this.addCommand(new GetGoodsInfo());
	}
}
