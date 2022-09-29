package io.github.h800572003.generator.utils;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class CoderUtilsTest {

    @Test
    void getJavaName() {
        String iphone = CoderUtils.getJavaName("iphone");
        Assertions.assertThat(iphone).isEqualTo("Iphone");
    }

    @Test
    void getJavaNameWithSuffix() {
        String iphone = CoderUtils.getJavaNameWithSuffix("iphone", "Controller");
        Assertions.assertThat(iphone).isEqualTo("IphoneController");
    }

    @Test
    void test_fileGetPath() {


        //GIVE
        String paackage = "io.github.h800572003";


        //WHEN
        String filePath = CoderUtils.getFilePath("../base", paackage);
        log.info("filePath:{}", filePath);

        //THEN
        Assertions.assertThat(filePath).isEqualTo("../base/io/github/h800572003");
    }
    @Test
    void getPath() {


        //GIVE
        String paackage = "io.github.h800572003";


        //WHEN
        String filePath = CoderUtils.getFilePath( paackage);
        log.info("filePath:{}", filePath);

        //THEN
        Assertions.assertThat(filePath).isEqualTo("io/github/h800572003");
    }
}