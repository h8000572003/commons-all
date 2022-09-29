package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.utils.FreeMarkerUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 建立新物件
 */
public class NewClass implements ICode {

    private NewPackage packageCode = new NewPackage(StringUtils.EMPTY);
    private Imports imports = new Imports();

    private GroupCode annotations = new GroupCode();

    private String protectedValue = Protecteds.PROTECTED.toJavaName();
    private String name;

    private String memo = "";

    private GroupCode body = new GroupCode();

    private MethodArgs constructorArgs = new MethodArgs();

    public NewClass addAnnotation(NewAnnotation newAnnotation) {
        annotations.add(newAnnotation);
        return this;
    }


    public NewClass addBody(ICode code) {

        this.body.add(code);
        return this;
    }

    public NewClass addBody(String text) {
        this.body.add(new NewStringLine(text));
        return this;
    }

    public NewClass(Protecteds protectedValue, String name) {
        this.protectedValue = protectedValue.toJavaName();
        this.name = name;
    }

    public NewClass addImport(String importString) {
        this.imports.addImport(importString);
        return this;
    }

    public NewClass setPackage(String protectedValue) {
        this.packageCode = new NewPackage(protectedValue);
        return this;
    }

    public void addConstructorArg(MethodArgs.MethodArg methodArg) {
        this.constructorArgs.add(methodArg);
    }

    @Override
    public String get() {
        return FreeMarkerUtils.toString(getClass().getSimpleName() + ".ftl", this);
    }


    public String getConstructorArgs() {
        return constructorArgs.get();
    }

    public String getProtectedValue() {
        return protectedValue;
    }

    public String getName() {
        return name;
    }


    public String getMemo() {
        return memo;
    }

    public NewClass setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public String getBody() {
        return body.get();
    }


    public String getHeader() {
        final GroupCode body = new GroupCode();
        body.add(packageCode);
        body.add(imports);
        if(StringUtils.isNoneBlank(memo)){
            body.add(new NewComment(true,memo));
        }
        body.add(annotations);
        return body.get();
    }

    public NewPackage getPackageCode() {
        return packageCode;
    }
}
