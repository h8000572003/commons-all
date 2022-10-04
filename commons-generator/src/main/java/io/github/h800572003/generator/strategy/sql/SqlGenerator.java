package io.github.h800572003.generator.strategy.sql;

import io.github.h800572003.generator.ICodeContext;
import io.github.h800572003.generator.ICodeGenerator;
import io.github.h800572003.generator.NewFileBuilder;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.new_code.MethodArgs;
import io.github.h800572003.generator.new_code.NewClass;
import io.github.h800572003.generator.new_code.NewMethod;
import io.github.h800572003.generator.new_code.NewStringLine;
import io.github.h800572003.generator.strategy.basedtogenerator.BaseDTOGenerator;
import io.github.h800572003.generator.strategy.basedtogenerator.BaseGenDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SqlGenerator implements ICodeGenerator {


    private SqlGeneratorDTO dto;

    private final ITypeResolver resolver;

    private final IJavaColumnResolver javaColumnResolver;

    BaseDTOGenerator baseDTOGenerator = new BaseDTOGenerator();

    private Map<String, String> rowMapper = new HashMap<>();

    public SqlGenerator(SqlGeneratorDTO dto) {
        this(dto, new JavaTypeResolver(), new JavaColumnResolver());
    }

    public void addRowMapper(String type, String value) {
        rowMapper.put(type, value);
    }


    public SqlGenerator(SqlGeneratorDTO dto, ITypeResolver resolver, IJavaColumnResolver javaColumnResolver) {
        this.dto = dto;
        this.resolver = resolver;
        this.javaColumnResolver = javaColumnResolver;


        baseDTOGenerator.setMemo(dto.getMemo());
        baseDTOGenerator.setPackageValue(dto.getPackageValue());
        baseDTOGenerator.setName(dto.getName());


        initJavaMapper();
    }

    private void initJavaMapper() {
        rowMapper.put(BigDecimal.class.getName(), "getBigDecimal");

        rowMapper.put(Integer.class.getName(), "getInt");
        rowMapper.put(int.class.getName(), "getInt");

        rowMapper.put(long.class.getName(), "getLong");
        rowMapper.put(Long.class.getName(), "getLong");

        rowMapper.put(Short.class.getName(), "getBigDecimal");

        rowMapper.put(Number.class.getName(), "getBigDecimal");

        rowMapper.put(Blob.class.getName(), "getBlob");
        rowMapper.put(String.class.getName(), "getString");
    }

    @Override
    public void generator(ICodeContext codeContext) {
        try (Connection connection = dto.getDataSource().getConnection()) {
            List<BaseGenDTO> baseGenDTOs = new ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                baseGenDTOs = getColmuns(statement);
                addColumns(baseGenDTOs);
                createImport(baseGenDTOs);
            }
            baseDTOGenerator.generator(codeContext);//產生DTO

            NewMethod newMethod = createMapRowMethod(baseGenDTOs);
            final NewClass newClass = codeContext.createNewFile()//
                    .setProtectedValue(Protecteds.PUBLIC)//
                    .setName(dto.getName() + "RowMapper")//
                    .build()//建立新檔案

                    .getNewClass()//

                    .setPackage(dto.getPackageValue())
                    .setMemo(dto.getMemo())
                    .addImport(RowMapper.class.getName())
                    .addImport(SQLException.class.getName())
                    .addImport(ResultSet.class.getName() )
                    .addImport(dto.getPackageValue() + "." + dto.getName())
                    .addImplements(String.format("RowMapper<%s>", dto.getName()))
                    .addBody(newMethod);

            //


        } catch (Exception e) {
            log.info("sql", e);
        }


    }

    private NewMethod createMapRowMethod(List<BaseGenDTO> baseGenDTOs) {
        NewMethod newMethod = new NewMethod(Protecteds.PUBLIC, dto.getName(), "mapRow")
                .addThrow("SQLException")
        .addMethodArg(new MethodArgs.MethodArg("ResultSet","rs"))
        .addMethodArg(new MethodArgs.MethodArg("int","rowNum"))
                .addBody(dto.getName() + " target = new " + dto.getName() + "()");
        for (BaseGenDTO baseGenDTO : baseGenDTOs) {
            String format = String.format("target.set%s(rs.%s(\"%s\"))",
                    StringUtils.capitalize(baseGenDTO.getName()),
                    getRowMapperOrDefault(baseGenDTO),
                    baseGenDTO.getColumnName()
            );
            newMethod.addBody(format);

        }
        newMethod.addBody("return " + "target");
        return newMethod;
    }

    private String getRowMapperOrDefault(BaseGenDTO baseGenDTO) {
        String defaultValue = "getObject";
        return rowMapper.getOrDefault(baseGenDTO.getTypeClass(), defaultValue);
    }

    private void addColumns(List<BaseGenDTO> baseGenDTOs) {
        baseGenDTOs.forEach(this.baseDTOGenerator::add);
    }

    private void createImport(List<BaseGenDTO> baseGenDTOs) {
        baseGenDTOs.forEach(i -> baseDTOGenerator.getImports().add(i.getTypeClass()));
    }

    private List<BaseGenDTO> getColmuns(Statement statement) throws SQLException {
        final List<BaseGenDTO> baseGenDTOs = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(dto.getSql());
        ResultSetMetaData metaData = resultSet.getMetaData();
        log.info("metaData:{}", metaData);
        int count = metaData.getColumnCount();
        for (int index = 1; index <= count; index++) {
            final String columnName = metaData.getColumnName(index);
            final String javaName = this.javaColumnResolver.getName(columnName);
            final int columnType = metaData.getColumnType(index);
            final String columnClassName = metaData.getColumnClassName(index);
            final JavaType javaType = resolver.getJavaType(columnClassName);
            final BaseGenDTO genDTO = BaseGenDTO.builder()
                    .columnName(columnName)//
                    .type(javaType.getTypeName())//
                    .typeClass(javaType.getClassName())//
                    .name(javaName)//
                    .isNum(javaType.isNum())//
                    .value("")//
                    .isAssign(false)
                    .memo(columnName)//
                    .build();//


            baseGenDTOs.add(genDTO);

        }
        return baseGenDTOs;
    }
}
