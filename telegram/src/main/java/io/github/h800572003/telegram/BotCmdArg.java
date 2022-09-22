package io.github.h800572003.telegram;

import org.apache.commons.lang3.StringUtils;

public class BotCmdArg {

	private String cmd;
	private String arg;

	public BotCmdArg(String fullyCmd) {
		String[] cmds = StringUtils.split(fullyCmd, "_", 2);
		this.cmd = cmds[0];
		this.arg = cmds.length > 1 ? cmds[1] : "";
	}

	public String getCmd() {
		return cmd;
	}

	public String getArg() {
		return arg;
	}

	@Override
	public String toString() {
		return "BotCmdArg [cmd=" + cmd + ", arg=" + arg + "]";
	}

}
