package io.github.h800572003.generator.strategy.sql;

import io.github.h800572003.generator.ICodeContext;
import io.github.h800572003.generator.ICodeGenerator;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.new_code.MethodArgs;
import io.github.h800572003.generator.new_code.NewFile;
import io.github.h800572003.generator.new_code.NewMethods;
import io.github.h800572003.generator.strategy.basedtogenerator.BaseDTOGenerator;
import io.github.h800572003.generator.strategy.basedtogenerator.BaseGenDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SqlGenerator implements ICodeGenerator {


    private SqlGeneratorDTO dto;

    private final ITypeResolver resolver;

    private final IJavaColumnResolver javaColumnResolver;

    private final BaseDTOGenerator baseDTOGenerator = new BaseDTOGenerator();

    private final IJavaResultSetResolver javaResultSetResolver;


    public SqlGenerator(SqlGeneratorDTO dto) {
        this(dto, new JavaTypeResolver(), new JavaColumnResolver(), new JavaResultSetResolver());
    }


    public SqlGenerator(SqlGeneratorDTO dto, ITypeResolver resolver, IJavaColumnResolver javaColumnResolver, IJavaResultSetResolver javaResultSetResolver) {
        this.dto = dto;
        this.resolver = resolver;
        this.javaColumnResolver = javaColumnResolver;
        this.javaResultSetResolver = javaResultSetResolver;


        baseDTOGenerator.setMemo(dto.getMemo());
        baseDTOGenerator.setPackageValue(dto.getPackageValue());
        baseDTOGenerator.setName(dto.getName());


    }


    @Override
    public void generator(ICodeContext codeContext) {
        final List<BaseGenDTO> baseGenDTOs = getBaseGenDTOS();
        createDTOFile(codeContext, baseGenDTOs);
        createMapperFile(codeContext, baseGenDTOs);


    }

    private void createMapperFile(ICodeContext codeContext, List<BaseGenDTO> baseGenDTOs) {
        final NewFile newFile = codeContext.createNewFile()//
                .setProtectedValue(Protecteds.PUBLIC)//
                .setName(dto.getName() + "RowMapper")//
                .build();//建立新檔案
        final NewMethods.NewMethod newMethod = createMapRowMethod(baseGenDTOs, newFile);
        newFile.getNewClass()//
                .setPackage(dto.getPackageValue())
                .setMemo(dto.getMemo())
                .addImport(RowMapper.class.getName())
                .addImport(SQLException.class.getName())
                .addImport(ResultSet.class.getName())
                .addImport(dto.getPackageValue() + "." + dto.getName())
                .addImplements(String.format("RowMapper<%s>", dto.getName()))

                .addBody(newMethod);
    }

    private void createDTOFile(ICodeContext codeContext, List<BaseGenDTO> baseGenDTOs) {
        addColumns(baseGenDTOs);
        createImport(baseGenDTOs);
        this.baseDTOGenerator.generator(codeContext);//產生DTO
    }

    private List<BaseGenDTO> getBaseGenDTOS() {
        try (Connection connection = dto.getDataSource().getConnection();
             final Statement statement = connection.createStatement()) {
            return getColmuns(statement);
        } catch (Exception e) {
            throw new SqlGeneratorException("sql:" + dto.getSql(), e);
        }

    }

    private NewMethods.NewMethod createMapRowMethod(List<BaseGenDTO> baseGenDTOs, NewFile newFile) {
        NewMethods.NewMethod newMethod = new NewMethods.NewMethod(Protecteds.PUBLIC, dto.getName(), "mapRow", null)
                .addThrow("SQLException")
                .addMethodArg(new MethodArgs.MethodArg("ResultSet", "rs"))
                .addMethodArg(new MethodArgs.MethodArg("int", "rowNum"))
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
        return this.javaResultSetResolver.getMethod(baseGenDTO.getTypeClass());

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
