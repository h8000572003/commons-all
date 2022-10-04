package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.utils.FreeMarkerUtils;

public class NewField implements ICode {


    private String protectedValue = Protecteds.PROTECTED.toJavaName();
    private final String name;
    private final String typeName;

    private String finalValue="";
    private String value="";

    private boolean isAssign=false;



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

        StringBuffer buffer=new StringBuffer();
        buffer.append(protectedValue+" ");
        buffer.append(finalValue+" ");
        buffer.append(typeName+" ");
        buffer.append(name);
        if(isAssign){
            buffer.append(" = "+value);
        }
        buffer.append(";");
        return buffer.toString();
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
        this.isAssign=true;
        return this;
    }
    public NewField setValue(int value){
        this.value=value+"";
        return this;
    }

    public NewField setAssign(boolean assign) {
        isAssign = assign;
        return this;
    }

    @Override
    public String toString() {
        return this.get();
    }
}
