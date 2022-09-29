package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 組合程式
 */
public class GroupCode implements ICode {

    private final Set<ICode> codes=new LinkedHashSet<>();


    @Override
    public String get() {
        return StringUtils.join(codes,"\n");
    }

    public void add(ICode code){
        this.codes.add(code);
    }


    @Override
    public String toString() {
        return this.get();
    }
}
