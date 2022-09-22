package io.github.h800572003.telegram;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class BotCmdRegister {
	private Map<String, IBotCmd> botCmds = new ConcurrentHashMap<>();
	private IBotCmd notFoundCmd;

	public BotCmdRegister() {
		super();
		this.notFoundCmd = (arg, context) -> new BotCmdResponse("請輸入正確指令");
	}

	public void register(String cmd, IBotCmd botCmd) {
		botCmds.put(cmd.toUpperCase(), botCmd);
	}

	public IBotCmd find(String cmd) {
		return botCmds.getOrDefault(cmd.toUpperCase(), notFoundCmd);
	}

	public void setNotFoundCmd(IBotCmd notFoundCmd) {
		this.notFoundCmd = notFoundCmd;
	}

	public BotCmdResponse execute(BotCmdArg botCmd, BotContext botContext) {
		return this.find(botCmd.getCmd()).execute(botCmd.getArg(), botContext);
	}

}
