package io.github.h800572003.generator.strategy.sql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class JavaColumnResolverTest {

    private JavaColumnResolver javaColumnResolver = new JavaColumnResolver();

    @Test
    void getName() {
        String student_id = javaColumnResolver.getName("STUDENT_ID");

        log.info("student_id:{}", student_id);
        assertThat(student_id.toString()).isEqualTo("studentId");
    }
}