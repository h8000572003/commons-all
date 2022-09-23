package io.github.h800572003.telegram;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelegramBotService implements IBotSendService {
	BotMedidata botMedidata;
	private TelegramLongPollingBot telegramLongPollingBot;
	private BotSession botSession;
	private final IBotEventListener botEventListener;
	private final IBotRepository botRepository;
	private final TelegramBotFormat telegramBotFormat;
	private final Function<Update, BotContext> functionBotContext;

	public TelegramBotService(IBotRepository botRepository, IBotEventListener botEventListener,
			TelegramBotFormat telegramBotFormat, Function<Update, BotContext> functionBotContext) {
		super();

		this.botRepository = botRepository;
		this.botEventListener = botEventListener;
		this.telegramBotFormat = telegramBotFormat;
		this.functionBotContext = functionBotContext;
		this.initBot(botRepository);
	}

	private void initBot(IBotRepository botRepository) {
		try {
			this.botMedidata = botRepository.getBotMedidata();
			if (this.botMedidata.isUseBot()) {
				final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
				final DefaultBotOptions defaultBotOptions = new DefaultBotOptions();
				initTg(defaultBotOptions);
				this.botSession = telegramBotsApi.registerBot(this.telegramLongPollingBot);
			}
		} catch (final Exception e) {
			log.error("error", e);
		}
	}

	private void initTg(DefaultBotOptions defaultBotOptions) {
		this.telegramLongPollingBot = new TelegramLongPollingBot(defaultBotOptions) {

			@Override
			public String getBotUsername() {
				return TelegramBotService.this.botMedidata.getUsername();
			}

			@Override
			public void onUpdateReceived(Update update) {
				final BotContext myBotContext = TelegramBotService.this.functionBotContext.apply(update);
				TelegramBotService.this.botEventListener.update(myBotContext, TelegramBotService.this);
			}

			@Override
			public String getBotToken() {
				return TelegramBotService.this.botMedidata.getToken();
			}
		};
	}

	@PreDestroy
	public void close() {
		if (this.botSession != null) {
			this.botSession.stop();
		}
	}

	public interface IBotEventListener {
		public void update(BotContext context, IBotSendService botService);
	}

	@Override
	public void respond(String message) {
		sendMessage(message, StringUtils.EMPTY);
	}

	private void sendMessage(String message, String executeUserId) {
		this.ifWithBot(() -> {
			final List<String> sendIds = getSendId(executeUserId);
			for (final String userId : sendIds) {
				try {
					final SendMessage sm = new SendMessage();
					sm.setChatId(userId);
					sm.setText(this.telegramBotFormat.format(message));
					this.telegramLongPollingBot.execute(sm);
				} catch (final TelegramApiException e) {
					log.info("send userid:{} error", userId, e);
				}
			}
		});
	}

	private List<String> getSendId(String executeUserId) {
		List<String> sendIds;
		if (StringUtils.isNotBlank(executeUserId)) {
			sendIds = this.botRepository.getUser().stream().filter(i -> !StringUtils.equals(i, executeUserId))
					.collect(Collectors.toList());
		} else {
			sendIds = this.botRepository.getUser().stream().collect(Collectors.toList());
		}
		return sendIds;
	}

	@Override
	public void respond(BotCmdResponse botCmdResponse, BotContext botContext) {
		this.ifWithBot(() -> {
			if (StringUtils.isNotBlank(botCmdResponse.getText())) {// 須回應
				final SendMessage sm = new SendMessage();
				sm.setChatId(botContext.getChatId());
				sm.setText(this.telegramBotFormat.format(botCmdResponse));
				try {
					this.telegramLongPollingBot.execute(sm);
				} catch (final TelegramApiException e) {
					log.info("send botCmdResponse:{}", botCmdResponse, e);
				}
				if (botCmdResponse.isNoticeUsers()) {
					this.sendMessage("發送通知:" + botContext.getUserName() + "呼叫[" + botContext.getText() + "]指令"
							+ botCmdResponse.getCallUsersMessage(), botContext.getChatId());
				}
			}
		});
	}

	private void ifWithBot(Runnable runnable) {
		if (this.telegramLongPollingBot != null) {
			runnable.run();
		}
	}

}
