package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.contract.Protecteds;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewMethodTest {
    NewMethod method = new NewMethod(Protecteds.PROTECTED, "hello");

    /**
     * 測試沒有說明與沒有參數
     */
    @Test
    void getWithoutMemoAndReturn() {

        //GIVE

        //WHEN
        String s = method.get();
        log.info("method:{}", method.get());


        //THEN
        assertThat(s).contains("protected void hello()");
        assertThat(s).contains(" //TODO");
    }

    @Test
    void getWithMemoAndReturn() {

        //GIVE MEMO
        method.setMemo("myMemo");

        //WHEN
        String s = method.get();
        log.info("method:{}", method.get());

        //THEN
        assertThat(s).contains(" //myMemo");
    }

    @Test
    void getWithBodyAndReturn() {


        //GIVE MEMO
        method.addBody(new NewStringLine("測試程式文字"));

        //WHEN
        String s = method.get();
        log.info("method:{}", method.get());

        //THEN
        assertThat(s).contains("測試程式文字");
    }

    @Test
    void getWithArgs() {


        //GIVE MEMO
        method.addBody("測試程式文字");
        method.addMethodArg(new MethodArgs.MethodArg("String","name"));

        //WHEN
        String s = method.get();
        log.info("method:{}", method.get());

        //THEN
        assertThat(s).contains("測試程式文字");
    }
    @Test
    void getNewAnnotation() {

        method.addAnnotation(new NewAnnotation("@Test"));
        String s = method.get();
        log.info("method:{}", method.get());
        assertThat(s).contains("@Test");
    }
}