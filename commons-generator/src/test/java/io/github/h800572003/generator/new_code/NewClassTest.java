package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.contract.Protecteds;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewClassTest {
    public static final String MEMO = "測試memo";
    NewClass apple = new NewClass(Protecteds.VOID, "Apple");

    @BeforeEach
    void init() {
        apple.setPackage(NewClassTest.class.getPackage().getName())//
                .addImport(NewClassTest.class.getName())//
                .addImport(NewClass.class.getName())//
                .addBody(//內容
                        new NewMethod(Protecteds.PUBLIC, "hello")//
                        .addMethodArg(new MethodArgs.MethodArg("String", "helloName")))//
                .addBody(new NewStringLine("//free"))

                .setMemo(MEMO)//
                .addAnnotation(new NewAnnotation("@Slf4j"))
                .addConstructorArg(new MethodArgs.MethodArg("String", "name"));
    }

    @Test
    void testMEMO() {
        String code = apple.get();
        log.info("code:{}", code);

        assertThat(code).contains("*" + MEMO);

    }

    @Test
    void testImport() {
        String code = apple.get();
        log.info("code:{}", code);

        assertThat(code).contains("import " + NewClassTest.class.getPackage().getName());

    }

    @Test
    void testAnnotation() {
        String code = apple.get();
        log.info("code:{}", code);

        assertThat(code).contains("@Slf4j");

    }
}


