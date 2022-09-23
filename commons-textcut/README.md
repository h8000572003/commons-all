## 文字切割元件
- 文字長度切割分別為byte與char


```java
ITextCutRulesSerive<TextLine> textCutRuleslSerive = new TextCutRuleslSerive<>();

	public TextCutRuleslSeriveTest2() {
		
		//加入規則
		textCutRuleslSerive.addRule(TextCutRuleslSeriveTest2::s01, getST01());
		textCutRuleslSerive.addRule(TextCutRuleslSeriveTest2::s02, getST02());
		textCutRuleslSerive.addRule(TextCutRuleslSeriveTest2::s03, getST03());

	}



public void test_give_4Line_reutrn_4Line() {

        // GIVE
        // ST011234567890,ST0212345,ST0312
        List<String> line = new ArrayList<String>();
        line.add("ST01~@1234567890");
        line.add("ST02~@12345");
        line.add("ST03~@12");
        line.add("ST01~@0987654321");

        // WHEN 轉換
        List<TextLine> list = textCutRuleslSerive.to(line);

        // THEN
        log.info("list:{}", list);

        assertThat(list.get(0).next().endsWith("ST01"));
        assertThat(list.get(0).next().endsWith("1234567890"));

        assertThat(list.get(1).next().endsWith("ST02"));
        assertThat(list.get(1).next().endsWith("12345"));

        assertThat(list.get(2).next().endsWith("ST03"));
        assertThat(list.get(2).next().endsWith("12"));

        assertThat(list.get(3).next().endsWith("ST01"));
        assertThat(list.get(3).next().endsWith("0987654321"));

        }
```