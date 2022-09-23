package io.github.h800572003.telegram;

public interface BotContext {

	/**
	 * 訊息
	 * 
	 * @return
	 */
	String getText();

	/**
	 * 使用者ID
	 * 
	 * @return
	 */
	String getUsrId();

	/**
	 * 對話姓名
	 * 
	 * @return
	 */
	String getUserName();

	/**
	 * 聊天室ID
	 * 
	 * @return
	 */
	String getChatId();

	/**
	 * 可否執行
	 * 
	 * @return
	 */
	boolean canExe();
}
