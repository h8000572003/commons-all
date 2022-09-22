package io.github.h800572003.telegram;

public interface IBotSendService {

	void respond(String message);

	void respond(BotCmdResponse botCmdResponse, BotContext botContext);
}
