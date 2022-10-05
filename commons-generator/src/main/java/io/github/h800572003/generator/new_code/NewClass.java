package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.utils.FreeMarkerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 建立新物件
 */
public class NewClass implements ICode {


    public NewMethods createNewMethods() {
        return methods;
    }

    public enum NewClassType{
        ABSTRACT("abstract class"),//
        CLASS("class"),//
        INTERFACE("interface"),//
        ENUM("enum"),//
        ;
        final String code;



        NewClassType(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return this.code;
        }
    }

    private NewPackage packageCode = new NewPackage(StringUtils.EMPTY);
    private final Imports imports = new Imports();

    private NewClassType newClassType=NewClassType.CLASS;

    private final GroupCode annotations = new GroupCode();


    private final NewConstructors newConstructors;

    private final NewMethods methods=new NewMethods(this);

    private String protectedValue = Protecteds.PROTECTED.toJavaName();
    private final String name;

    private String memo = "";

    private final GroupCode body = new GroupCode();

    private final MethodArgs constructorArgs = new MethodArgs();

    private  NewImplements implement =new NewImplements();

    private NewExtend extend=new NewExtend("");

    public NewClass addAnnotation(NewAnnotation newAnnotation) {
        annotations.add(newAnnotation);
        return this;
    }
    public NewConstructors.NewConstructorGroup createNewConstructors() {
        NewConstructors.NewConstructorGroup args = newConstructors.createArgs();
        return args;
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
        this.newConstructors=new NewConstructors(name,this);
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

        StringBuffer buffer=new StringBuffer();
        buffer.append(getHeader());
        buffer.append("\n");
        buffer.append(StringUtils.isNotBlank(protectedValue)?protectedValue+" ":"");
        buffer.append(StringUtils.isNotBlank(newClassType.toString())?newClassType.toString()+" ":"");
        buffer.append(name+" ");
        buffer.append(extend+" ");
        buffer.append(implement+" ");
        buffer.append("{\n");
        buffer.append(newConstructors.toString()+"\n");

        buffer.append(body.get());


        buffer.append("}\n");

//        return FreeMarkerUtils.toString(getClass().getSimpleName() + ".ftl", this);
        return buffer.toString();
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
        if (StringUtils.isNoneBlank(memo)) {
            body.add(new NewComment(true, memo));
        }
        body.add(annotations);
        return body.get();
    }

    public NewPackage getPackageCode() {
        return packageCode;
    }

    public NewClass addImplements(String implement) {
        this.implement.add(implement);
        return this;
    }


    public NewImplements getImplement() {
        return implement;
    }

    public NewClass setExtend(String extend) {
        this.extend=new NewExtend(extend);
        return this;
    }

    public NewExtend getExtend() {
        return extend;
    }

    public void setNewClassType(NewClassType newClassType) {
        this.newClassType = newClassType;
    }
}
