package io.github.h800572003.generator.strategy.sql;

import lombok.Data;
import org.springframework.jdbc.core.JdbcOperations;

import javax.sql.DataSource;

@Data
public class SqlGeneratorDTO {
    private DataSource dataSource;


    private String memo;
    private String name;
    private String packageValue;


    private String sql;

    private JdbcOperations jdbcOperations;
}
