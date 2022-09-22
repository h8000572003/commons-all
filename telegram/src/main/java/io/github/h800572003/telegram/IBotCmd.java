package io.github.h800572003.telegram;

/**
 * 機器人命令
 * 
 * @author andy
 *
 */
public interface IBotCmd {

	/**
	 * 
	 * @param arg
	 *            命令
	 * @param context
	 *            訊息上下文
	 * @return 命令回訊方式
	 */
	BotCmdResponse execute(String arg, BotContext context);
}
