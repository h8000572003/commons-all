package io.github.h800572003.telegram;

import org.apache.commons.lang3.StringUtils;

public class BotCmd {

	private String cmd;
	private String text;

	public BotCmd(String cmd, String arg, String text) {
		this.cmd = cmd;
		if (StringUtils.isNotBlank(arg)) {
			this.cmd += "_" + arg;
		}
		this.text = text;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "" + this.cmd + " -" + this.text;
	}

}
