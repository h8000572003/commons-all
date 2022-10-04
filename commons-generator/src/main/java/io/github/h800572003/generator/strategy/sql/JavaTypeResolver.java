package io.github.h800572003.generator.strategy.sql;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JavaTypeResolver implements ITypeResolver {

    private Map<String, JavaTypeMapper> javaTypeMap = new HashMap<>();
    private Set<String> numSet = new HashSet<>();

    public JavaTypeResolver() {
        numSet.add(int.class.getName());
        numSet.add(float.class.getName());
        numSet.add(double.class.getName());
        numSet.add(long.class.getName());
        numSet.add(BigDecimal.class.getName());
        numSet.add(Number.class.getName());
    }

    @FunctionalInterface
    public interface JavaTypeMapper {
        String to(String old);
    }

    public void add(String className, JavaTypeMapper javaTypeMapper) {
        this.javaTypeMap.put(className, javaTypeMapper);
    }

    public static class NowMapper implements JavaTypeMapper {

        @Override
        public String to(String old) {
            return old;
        }
    }


    public boolean isNUm(String className) {
        return numSet.contains(className);
    }

    @Override
    public JavaType getJavaType(String classType) {
        return new JavaType(javaTypeMap.getOrDefault(classType, new NowMapper()).to(classType), this);
    }

    public String getType(String className) {
        final String[] split = StringUtils.split(className, ".");
        return split[split.length - 1];
    }

}
