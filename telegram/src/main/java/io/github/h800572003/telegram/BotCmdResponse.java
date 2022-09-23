package io.github.h800572003.telegram;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class BotCmdResponse {
	private final String text;
	private final List<BotCmd> nextCmds;
	private String noticeUsersMessage = "";
	private boolean isNoticeUsers;

	public static BotCmdResponse error(Throwable throwable) {
		return new BotCmdResponse(throwable.getMessage());
	}


	public static BotCmdResponse withNext(List<BotCmd> cmds) {
		return new BotCmdResponse("請選擇下一步,", cmds);
	}

	public static BotCmdResponse message(String message) {
		return new BotCmdResponse(message);
	}

	public BotCmdResponse() {
		this(StringUtils.EMPTY);
	}

	public BotCmdResponse(String text) {
		this(text, Arrays.asList());
	}

	public BotCmdResponse(String text, List<BotCmd> nextCmd) {
		this.text = text;
		this.nextCmds = nextCmd;
	}

	public List<BotCmd> getNextCmds() {
		return nextCmds;
	}

	public String getCallUsersMessage() {
		return noticeUsersMessage;
	}

	public BotCmdResponse withNoticeUsersMessage(String callUsersMessage) {
		this.noticeUsersMessage = callUsersMessage;
		this.isNoticeUsers = true;
		return this;
	}

	public String getText() {
		return text;
	}

	public boolean isNoticeUsers() {
		return isNoticeUsers;
	}


}
