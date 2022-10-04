package io.github.h800572003.generator.strategy.sql;

import java.sql.Types;

public interface ITypeResolver {


    JavaType getJavaType(String type);
}
