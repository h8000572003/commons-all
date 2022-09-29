package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.utils.FreeMarkerUtils;

public class NewField implements ICode {


    private String protectedValue = Protecteds.PROTECTED.toJavaName();
    private String name;
    private String typeName;

    private String finalValue="";
    private String value="";


    public NewField(Protecteds protectedValue, String name, String typeName, boolean isFinal, String value) {
        this.protectedValue = protectedValue.toJavaName();
        this.name = name;
        this.typeName = typeName;
        this.value=value;
        this.setFinal(isFinal);
    }
    public NewField(Protecteds protectedValue, String name, String typeName){
        this(protectedValue,name,typeName,false,"");
    }



    @Override
    public String get() {
        return FreeMarkerUtils.toString(getClass().getSimpleName()+".ftl", this);
    }

    public String getProtectedValue() {
        return protectedValue;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }


    public NewField setFinal(boolean isFinal) {
        if(isFinal){
            finalValue="final";
        }else{
            finalValue="";
        }
        return this;
    }

    public String getValue() {
        return value;
    }

    public String getFinalValue() {
        return finalValue;
    }

    public NewField setValue(String value) {
        this.value = "\""+value+"\"";
        return this;
    }
    public NewField setValue(int value){
        this.value=value+"";
        return this;
    }

    @Override
    public String toString() {
        return this.get();
    }
}
