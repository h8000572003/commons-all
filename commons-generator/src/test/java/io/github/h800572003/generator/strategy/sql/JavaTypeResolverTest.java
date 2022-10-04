package io.github.h800572003.generator.strategy.sql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Types;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JavaTypeResolverTest {

    private JavaTypeResolver javaTypeResolver = new JavaTypeResolver();

    @Test
    void getJavaTypeString() {

        //GIVE
        String input = String.class.getName();

        //WHEN
        JavaType javaType = javaTypeResolver.getJavaType(input);


        //THEN
        JavaType excep = new JavaType(//
                String.class.getName(), //
                javaTypeResolver);//
        log.info("excp:{}",excep);
        assertThat(javaType).isEqualTo(excep);
    }
    @Test
    void getJavaTypeInt() {

        //GIVE
        String input = int.class.getName();

        //WHEN
        JavaType javaType = javaTypeResolver.getJavaType(input);


        //THEN
        JavaType excep = new JavaType(//
                int.class.getName(), //
                javaTypeResolver);//
        log.info("excp:{}",excep);
        assertThat(javaType).isEqualTo(excep);
    }
    @Test
    void getJavaTypeBig() {

        //GIVE
        String input = BigDecimal.class.getName();

        //WHEN
        JavaType javaType = javaTypeResolver.getJavaType(input);


        //THEN
        JavaType excep = new JavaType(//
                BigDecimal.class.getName(), //
                javaTypeResolver);//
        log.info("excp:{}",excep);
        assertThat(javaType).isEqualTo(excep);
    }
}