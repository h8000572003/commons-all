package io.github.h800572003.generator.new_code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewAnnotationTest {

    @Test
    void get() {

        NewAnnotation annotation=new NewAnnotation("@Slf4j");
        String actual = annotation.get();
        log.info(actual);
        assertThat(actual).contains("@Slf4j");
    }
}