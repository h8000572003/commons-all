package io.github.h800572003.telegram;

public interface TelegramBotFormat {

	String format(BotCmdResponse botCmdResponse);

	String format(String text);
}
