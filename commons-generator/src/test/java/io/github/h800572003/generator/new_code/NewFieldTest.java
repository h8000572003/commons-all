package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.contract.Protecteds;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewFieldTest {


    @Test
    void test() {
        NewField field = new NewField(Protecteds.PUBLIC, "name", String.class.getSimpleName());
        String s = field.get();
        log.info("s:{}", s.toString());
        assertThat(s).contains("public  String name;");
    }
    @Test
    void testValue() {
        NewField field = new NewField(Protecteds.PUBLIC, "name", String.class.getSimpleName());
        field.setValue("Mark");
        String s = field.get();
        log.info("s:{}", s.toString());
        assertThat(s).contains("public  String name=\"Mark\";");
    }


}