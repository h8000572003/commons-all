package io.github.h800572003.concurrent.group;

import io.github.h800572003.exception.ApBusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * 根據key分類
 * @param <T>
 */
public class SegmentKeyGroupBase<T> implements IClassificationStrategy<T> {

    private int index = 0;


    private SegmentKeyFilter filter;
    private int size = 0;


    Map<String, Integer> map = new HashMap<>();


    public SegmentKeyGroupBase(SegmentKeyFilter filter, int size) {
        this.filter = filter;
        this.size = size;
        if (size < 2) {
            throw new ApBusinessException("分群不得小於2");
        }
        if (filter == null) {
            throw new ApBusinessException("轉換器不得空白");
        }


    }

    @FunctionalInterface
    public interface SegmentKeyFilter<T> {
        String toKey(T t);
    }


    @Override
    public Integer toCategory(T data) {
        String key = this.filter.toKey(data);
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            int group = index++ % size;
            map.put(key, group);
            return group;
        }
    }


}
