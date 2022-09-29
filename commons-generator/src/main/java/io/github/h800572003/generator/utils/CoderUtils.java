package io.github.h800572003.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;

public enum CoderUtils {
    ;

    public static String getJavaName(String name) {
        return StringUtils.capitalize(name);
    }

    /**
     * 有後綴文字
     *
     * @param name
     * @return
     */
    public static String getJavaNameWithSuffix(String name, String suffix) {
        return getJavaName(name) + suffix;
    }


    public static String getFilePath(String base, String paackage) {
        return Paths.get(base, StringUtils.replace(paackage, ".", "/")).toString();
    }

    public static String getFilePath(String paackage) {
        return Paths.get(StringUtils.replace(paackage, ".", "/")).toString();
    }
}
