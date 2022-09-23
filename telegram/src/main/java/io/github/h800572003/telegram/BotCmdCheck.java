package io.github.h800572003.telegram;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BotCmdCheck {

	private final IBotRepository botRepository;
	private final List<String> excludes = new ArrayList<>();
	public void add(String cmd) {
		excludes.add(cmd);
	}

	public BotCmdCheck(IBotRepository botRepository) {
		super();
		this.botRepository = botRepository;
	}

	boolean check(BotCmdArg botCmdArg, BotContext botContext) {
		if (excludes.contains(botCmdArg.getCmd())) {
			return true;
		}
		return botRepository.isAuthorize(botContext.getUsrId());

	}

}
