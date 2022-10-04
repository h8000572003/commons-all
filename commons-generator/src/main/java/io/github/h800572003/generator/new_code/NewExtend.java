package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import org.apache.commons.lang3.StringUtils;

/**
 * 建立繼承
 */
public class NewExtend implements ICode {
    private String extend;

    public NewExtend(String extend) {
        this.extend = extend;
    }


    @Override
    public String get() {
        return StringUtils.isNotBlank(extend) ? "extends " + extend : "";
    }

    @Override
    public String toString() {
        return get();
    }
}
