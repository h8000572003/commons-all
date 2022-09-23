package io.github.h800572003.telegram;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BotCmdService {
	private final BotCmdRegister botCmdRegister;
	private final BotCmdCheck botCmdCheck;

	public BotCmdService(BotCmdRegister botCmdRegister, BotCmdCheck botCmdCheck) {
		super();
		this.botCmdRegister = botCmdRegister;
		this.botCmdCheck = botCmdCheck;
	}

	public BotCmdResponse exeCmd(String fullyCmd, BotContext botContext) {
		BotCmdArg botCmdArg = new BotCmdArg(fullyCmd);
		if (!botCmdCheck.check(botCmdArg, botContext)) {
			return BotCmdResponse.message("無操作權限");
		}
		log.info("BotCmdArg :{}", botCmdArg);
		try {
			return botCmdRegister.execute(botCmdArg, botContext);
		} catch (Exception e) {
			log.error("fullyCmd:{}", fullyCmd, e);
			return BotCmdResponse.error(e);
		}

	}

}
