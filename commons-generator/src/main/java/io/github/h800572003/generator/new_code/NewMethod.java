package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.utils.FreeMarkerUtils;
import org.apache.commons.lang3.StringUtils;

public class NewMethod implements ICode {

    private String protectedValue = Protecteds.PROTECTED.toJavaName();
    private String name;
    private String returnType;

    private String memo="";

    private GroupCode annotations = new GroupCode();

    private GroupCode body=new GroupCode();

    private MethodArgs args = new MethodArgs();


    public NewMethod(Protecteds protectedValue, String name) {
        this(protectedValue,"void",name);
    }

    public NewMethod addBody(ICode code){
        this.body.add(code);
        return this;
    }
    public NewMethod addBody(String text){
        this.body.add(new NewStringLine(text));
        return this;
    }
    public NewMethod addAnnotation(NewAnnotation newAnnotation) {
        annotations.add(newAnnotation);
        return this;
    }

    public NewMethod(Protecteds protectedValue, String returnType, String name) {
        this.protectedValue = protectedValue.toJavaName();
        this.name = name;
        this.returnType = returnType;
    }

    public NewMethod addMethodArg(MethodArgs.MethodArg methodArg){
        this.args.add(methodArg);
        return this;
    }

    @Override
    public String get() {
        return FreeMarkerUtils.toString(getClass().getSimpleName()+".ftl", this);
    }


    public String getArgs() {
        return args.get();
    }

    public String getProtectedValue() {
        return protectedValue;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getMemo() {
        return memo;
    }

    public NewMethod setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public String getHeader() {
        final GroupCode body = new GroupCode();
        if(StringUtils.isNoneBlank(memo)){
            body.add(new NewComment(true,memo));
        }
        body.add(annotations);
        return body.get();
    }

    public String getBody(){
        return body.get();
    }


    @Override
    public String toString() {
        return get();
    }
}

