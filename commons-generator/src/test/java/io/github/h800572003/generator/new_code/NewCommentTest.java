package io.github.h800572003.generator.new_code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewCommentTest {

    @Test
    void getIsNotMul() {

        NewComment comment=new NewComment(false,"hello");
        String code = comment.get();
        log.info(code);

        assertThat(code).contains("//hello");
    }
    @Test
    void getIsMul() {

        NewComment comment=new NewComment(true,"321","123");
        String code = comment.get();
        log.info(code);

        assertThat(code).contains("*321");
        assertThat(code).contains("*123");
    }


}