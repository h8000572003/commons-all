package io.github.h800572003.generator.strategy.basedtogenerator;

import io.github.h800572003.generator.ICodeContext;
import io.github.h800572003.generator.ICodeGenerator;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.new_code.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Set;

/**
 * 基礎DTO產生
 */
public class BaseDTOGenerator implements ICodeGenerator {

    private String memo;
    private String name;
    private String packageValue;


    private Set<String> imports = new LinkedHashSet<>();

    private List<BaseGenDTO> baseGenDTOs = new ArrayList<>();

    private NewClass newClass;

    @Override
    public void generator(ICodeContext codeContext) {
        this.newClass = codeContext.createNewFile()//
                .setProtectedValue(Protecteds.PUBLIC)//
                .setName(name)//
                .build()//建立新檔案

                .getNewClass()//

                .setPackage(packageValue)
                .setMemo(memo);//

        for (String importString : imports) {
            newClass.addImport(importString);
        }

        addField(newClass);
        this.addGet(newClass);
        this.addSet(newClass);
    }

    private void addSet(NewClass newClass) {
        for (final BaseGenDTO baseGenDTO : baseGenDTOs) {
            newClass.addBody(new NewComment(false, baseGenDTO.getMemo()));
            newClass.createNewMethods()
                    .createMethod(Protecteds.PUBLIC, "set" + StringUtils.capitalize(baseGenDTO.getName()))
                    .addMethodArg(new MethodArgs.MethodArg(baseGenDTO.getType(), baseGenDTO.getName()))
                    .addBody("this." + baseGenDTO.getName() + "=" + baseGenDTO.getName());
        }
    }

    private void addGet(NewClass newClass) {
        for (final BaseGenDTO baseGenDTO : baseGenDTOs) {
            newClass.addBody(new NewComment(false, baseGenDTO.getMemo()));
            newClass.createNewMethods()
                    .createMethod(Protecteds.PUBLIC, baseGenDTO.getType(), "get" + StringUtils.capitalize(baseGenDTO.getName()))
                    .addBody("return this." + baseGenDTO.getName());
        }
    }

    private void addField(NewClass newClass) {
        for (final BaseGenDTO baseGenDTO : baseGenDTOs) {
            newClass.addBody(new NewComment(false, baseGenDTO.getMemo()));
            final NewField newField = new NewField(Protecteds.PRIVATE, baseGenDTO.getName(), baseGenDTO.getType()).setFinal(false);
            if (baseGenDTO.isNum()) {
                newClass.addBody(
                        newField.setValue(Integer.valueOf(baseGenDTO.getValue()))
                );
            } else {
                newClass.addBody(newField
                        .setValue(baseGenDTO.getValue())
                );
            }
            newField.setAssign(StringUtils.isNotBlank(baseGenDTO.getValue()));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public void add(BaseGenDTO baseGenDTO) {
        this.baseGenDTOs.add(baseGenDTO);
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public Set<String> getImports() {
        return imports;
    }

    public NewClass getNewClass() {
        return newClass;
    }
}
