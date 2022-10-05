package io.github.h800572003.generator.new_code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class NewConstructorsTest {


    private NewConstructors newConstructors=new NewConstructors("Apple",null);
    @Test
    void get() {

        NewConstructors root = newConstructors
                .createArgs()
                .add("String", "name").add("int", "age")


                .addBody("//STEP1")
                .addBody("//STEP2").getPrevious()
                .createArgs()
                .add("String","name")
                .addBody("//SEC2")
                .getPrevious();


        String code = root.toString();


        log.info("code:{}",code);

        assertThat(code).contains("Apple(String name,int age) {");
        assertThat(code).contains("//STEP1;");
        assertThat(code).contains("//STEP2;");


        assertThat(code).contains("Apple(String name) {");
        assertThat(code).contains("//SEC2;");
    }
}