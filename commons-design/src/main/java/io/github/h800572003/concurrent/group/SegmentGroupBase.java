package io.github.h800572003.concurrent.group;

import java.util.List;

public class SegmentGroupBase<T> implements IClassificationStrategy<T> {
    private int index;

    private List<T> list;
    private final int size;

    public SegmentGroupBase(List<T> list, int size) {
        this.list = list;
        this.size=size;
    }

    public  Integer toCategory(T s){
        return index++%size;
    }

}
