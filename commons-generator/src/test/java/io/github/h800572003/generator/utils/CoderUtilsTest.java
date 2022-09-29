package io.github.h800572003.generator.utils;


import io.github.h800572003.generator.utils.CoderUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CoderUtilsTest {

    @Test
    void getJavaName() {
        String iphone = CoderUtils.getJavaName("iphone");
        Assertions.assertThat(iphone).isEqualTo("Iphone");
    }

    @Test
    void getJavaNameWithSuffix() {
        String iphone = CoderUtils.getJavaNameWithSuffix("iphone","Controller");
        Assertions.assertThat(iphone).isEqualTo("IphoneController");
    }
}