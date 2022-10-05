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
//
                .createNewConstructors()
                .add("String", "name")
                .addBody("//待撰寫")
                .getPrevious()//
                //第二組
                .createArgs().add("int","age")
                .addBody("//TOD")
                .getPrevious()
                .getPrevious()




                .createNewMethods()
                .createMethod(Protecteds.PUBLIC, "hello")
                .addMethodArg(new MethodArgs.MethodArg("String", "helloName"))//
                .addBody("//free")
                .setMemo(MEMO)
                .getPrevious()//
                .getPrevious()//


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

    @Test
    void testImplements() {
        apple.addImplements("ICodeGenerator2")
                .addImplements("ICodeGenerator");

        String code = apple.get();
        log.info("code:{}", code);
        assertThat(code).contains("implements ICodeGenerator2,ICodeGenerator {");
    }

    @Test
    void testExtend() {
        apple.setExtend("Object");
        String code = apple.get();


        log.info("code:{}", code);
        assertThat(code).contains("class Apple extends Object");
    }


}


