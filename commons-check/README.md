## commons-check


共用檢查元件




### ValidationService



```java
@Slf4j
class ValidationServiceTest {

	@Test
	void testGiveBlankDTOThen2ErrorThenWith2ErrorSize() {

		// GIVE
		final CheckDTO dto = new CheckDTO();

		// THEN
		ValidationStrategy<CheckDTO> create = this.create();

		// THEN
		IValidationService validationService = new ValidationService();
		boolean execute = validationService.execute(dto, create);
		assertThat(execute).isFalse();

	}

    /**
     * 建立驗證規則
     * @return
     */
	public ValidationStrategy<CheckDTO> create() {
		final Builder<CheckDTO> builder = new Builder<>();
		builder.checkContinue(i -> CheckResult.of("X1", "名稱不得空白", () -> CheckRoles.isNotNull(i.getName())));
		builder.checkContinue(i -> CheckResult.of("X2", "名稱不得空白", () -> CheckRoles.isBetween(i.getName(), 0, 10)));
		builder.setHandler(t -> {
			assertThat(t.getErrors()).hasSize(2);
			assertThat(t.isAllError()).isTrue();
		});
		return builder.build();
	}
}




```