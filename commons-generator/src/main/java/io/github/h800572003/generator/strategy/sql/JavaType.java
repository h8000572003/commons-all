package io.github.h800572003.generator.strategy.sql;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(exclude = {"className"})

public class JavaType {
    private String typeName;

    private String className;

    private boolean isNum;


    public JavaType(String className, JavaTypeResolver javaTypeResolver) {
        String[] split = StringUtils.split(className, ".");
        this.typeName = javaTypeResolver.getType(className);
        this.className = className;
        this.isNum = javaTypeResolver.isNUm(className);

    }

    public boolean isNum() {
        return this.isNum;
    }


    public String getTypeName() {
        return typeName;
    }


    @Override
    public String toString() {
        return this.className;
    }

    public String getClassName() {
        return this.className;
    }
}
