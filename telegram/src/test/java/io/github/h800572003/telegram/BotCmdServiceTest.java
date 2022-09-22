package io.github.h800572003.telegram;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BotCmdServiceTest {

	IBotCmd botCmd;

	BotCmdRegister botCmdRegister;

	BotCmdService cmdService;

	BotContext botContext;

	BotCmdCheck botCmdCheck;

	@BeforeEach
	public void init() {
		this.botCmd = Mockito.mock(IBotCmd.class);

		this.botCmdRegister = new BotCmdRegister();
		this.botCmdRegister.register("/fake", this.botCmd);

		this.botContext = Mockito.mock(BotContext.class);

		this.botCmdCheck = Mockito.mock(BotCmdCheck.class);

		Mockito.when(this.botCmdCheck.check(Mockito.any(), Mockito.any())).thenReturn(true);

		this.cmdService = new BotCmdService(this.botCmdRegister, this.botCmdCheck);
	}

	@Test
	void test_give_fakeCmd_without_args_then_time1() {
		// arrange
		final String cmd = "/fake";

		// act
		this.cmdService.exeCmd(cmd, this.botContext);

		// assert
		Mockito.verify(this.botCmd, Mockito.times(1)).execute("", this.botContext);

	}

	@Test
	void test_give_fakeCmd_wit_args_then_time1() {

		// arrange
		final String cmd = "/fake_abcd?sdsd";

		// act
		this.cmdService.exeCmd(cmd, this.botContext);

		// assert
		Mockito.verify(this.botCmd, Mockito.times(1)).execute("abcd?sdsd", this.botContext);
	}

	@Test
	void test_give_noknow_then_mockResponse() {
		final BotCmdResponse botCmdResponse = new BotCmdResponse("XXX");

		final IBotCmd noKnow = Mockito.mock(IBotCmd.class);
		Mockito.when(noKnow.execute("", this.botContext)).thenReturn(botCmdResponse);

		this.botCmdRegister.setNotFoundCmd(noKnow);

		final String cmd = "/noknow_";
		final BotCmdResponse exeCmd = this.cmdService.exeCmd(cmd, this.botContext);

		assertThat(botCmdResponse).isEqualTo(exeCmd);
	}

}
