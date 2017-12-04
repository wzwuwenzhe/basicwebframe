package com.deady.command.chain;

import org.apache.commons.chain.impl.ChainBase;

/**
 * @author Andre.Z 2017-3-27 上午9:48:48<br>
 * 
 */
public abstract class PlugCommandChain extends ChainBase {

	public PlugCommandChain() {
		this.setChainCommands();
	}

	public abstract void setChainCommands();

}
