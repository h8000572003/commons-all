package io.github.h800572003.generator.strategy.sql;

import com.google.common.base.CaseFormat;

public class JavaColumnResolver implements IJavaColumnResolver {
    @Override
    public String getName(String column) {
        return  CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,column);
    }
}
