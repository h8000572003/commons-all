package io.github.h800572003.generator.strategy.sql;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;

public class JavaResultSetResolver implements IJavaResultSetResolver {
    private Map<String, String> javaResultSetMap = new HashMap<>();

    public JavaResultSetResolver() {
        javaResultSetMap.put(BigDecimal.class.getName(), "getBigDecimal");
        javaResultSetMap.put(Integer.class.getName(), "getInt");
        javaResultSetMap.put(int.class.getName(), "getInt");
        javaResultSetMap.put(long.class.getName(), "getLong");
        javaResultSetMap.put(Long.class.getName(), "getLong");
        javaResultSetMap.put(Short.class.getName(), "getBigDecimal");
        javaResultSetMap.put(Number.class.getName(), "getBigDecimal");
        javaResultSetMap.put(Blob.class.getName(), "getBlob");
        javaResultSetMap.put(String.class.getName(), "getString");
    }

    public void addTypeAndMethod(String type, String value) {
        javaResultSetMap.put(type, value);
        
        
    }
    @Override
    public String getMethod(String typeClass) {
        return this.javaResultSetMap.getOrDefault(typeClass,"getObject");
    }
}
