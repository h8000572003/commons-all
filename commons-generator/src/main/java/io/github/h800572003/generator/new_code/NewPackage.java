package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import org.apache.commons.lang3.StringUtils;

public class NewPackage implements ICode {
    private String packageString="";

    public NewPackage(String packageString) {
        this.packageString = packageString;
    }
    public NewPackage() {
       this(StringUtils.EMPTY);
    }

    @Override
    public String get() {
        if(StringUtils.isNoneBlank(packageString)){
            return "package "+packageString+";";
        }
        return "";
    }

    @Override
    public String toString() {
        return this.get();
    }


    public String getPackageString() {
        return packageString;
    }
}
