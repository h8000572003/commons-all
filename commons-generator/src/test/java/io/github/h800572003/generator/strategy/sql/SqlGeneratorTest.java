package io.github.h800572003.generator.strategy.sql;

import io.github.h800572003.generator.FileGeneratorOutput;
import io.github.h800572003.generator.NewFileGenerator;
import io.github.h800572003.generator.StringGeneratorOutput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Disabled
class SqlGeneratorTest {
    StringGeneratorOutput output = new StringGeneratorOutput();

    @Test
    void generator() throws ClassNotFoundException {
        final SqlGenerator sqlGenerator = createSql();


        //WHEN
        final NewFileGenerator generator = new NewFileGenerator(sqlGenerator, output);
        generator.export();

        //THEN
        log.info("out:{}", output.toString());
        assertThat(output.toString()).contains("name;");
        assertThat(output.toString()).contains("studentId;");
        assertThat(output.toString()).contains("mail;");
        assertThat(output.toString()).contains("tel;");
        assertThat(output.toString()).contains("age;");
        assertThat(output.toString()).isNotBlank();


        log.info("create table ok");
    }

    private static SqlGenerator createSql() {
        final DataSource datasource = createDatasource();
        creatTable(datasource);


        //GIVE
        final SqlGeneratorDTO sqlGeneratorDTO = new SqlGeneratorDTO();
        sqlGeneratorDTO.setDataSource(datasource);
        sqlGeneratorDTO.setSql("SELECT * from Customer c  ");
        sqlGeneratorDTO.setPackageValue("io.github.h800572003");
        sqlGeneratorDTO.setName("CustomerDTO");
        sqlGeneratorDTO.setMemo("顧客");
        final SqlGenerator sqlGenerator = new SqlGenerator(sqlGeneratorDTO);
        return sqlGenerator;
    }

    @Test
    @Disabled
    void generatorFile() throws ClassNotFoundException {
        final SqlGenerator sqlGenerator = createSql();

        new NewFileGenerator(sqlGenerator,new FileGeneratorOutput("./src/main/java")).export();

    }

    private static DataSource createDatasource() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSourceProperties.setUsername("sa");
        dataSourceProperties.setPassword("sa");
        dataSourceProperties.setDriverClassName("org.h2.Driver");

        DataSource datasource = dataSourceProperties.initializeDataSourceBuilder().build();
        return datasource;
    }

    private static void creatTable(DataSource datasource) {
        try (Connection connection = datasource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE Customer IF EXISTS;\n" +
                        "CREATE TABLE `Customer` (\n" +
                        "  `name` varchar(100) NOT NULL COMMENT '姓名',\n" +
                        "  `student_id` varchar(100) NOT NULL COMMENT '帳號',\n" +
                        "  `mail` varchar(100) DEFAULT NULL COMMENT '信箱',\n" +
                        "  `tel` varchar(100) DEFAULT NULL COMMENT '電話',\n" +
                        "  `age` INT DEFAULT NULL COMMENT '年紀'\n" +
                        ") ;");

            }




        } catch (Exception e) {
            log.info("e:", e);
        }
    }

}