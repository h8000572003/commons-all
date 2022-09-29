package io.github.h800572003.generator.new_code;



import com.google.common.collect.Lists;
import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.utils.FreeMarkerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 建立備註
 */
public class NewComment implements ICode {
    private boolean isMultiLine = false;

    private final List<String> comments = new ArrayList<>();

    public NewComment(boolean isMultiLine, String...comment) {
        this.isMultiLine = isMultiLine;
        this.comments.addAll(Lists.newArrayList(comment));
    }


    @Override
    public String get() {
        return FreeMarkerUtils.toString(getClass().getSimpleName() + ".ftl", this);
    }

    public boolean isMultiLine() {
        return isMultiLine;
    }

    public List<String> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return this.get();
    }
}
