package io.github.h800572003.generator.utils;

import org.apache.commons.lang3.StringUtils;

public class CoderUtils {
    public static String getJavaName(String name){
        return StringUtils.capitalize(name);
    }

    /**
     * 有後綴文字
     * @param name
     * @return
     */
    public static String getJavaNameWithSuffix(String name,String suffix){
        return getJavaName(name)+suffix;
    }



}
