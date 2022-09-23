package io.github.h800572003.concurrent.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基本分群
 *
 * @param <T>
 */
public class SegmentGroup<T> implements IGroup<T> {

    private final List<T> src;//原始檔案
    private int size;//檔案大小
    private int index = 0;
    private final Map<Integer, IGroupTaskContext<T>> map = new HashMap<>();//分群檔案

    private final IClassificationStrategy<T> strategy;//分群策略


    /**
     * @param src      資料
     * @param strategy 分群策略
     */
    public SegmentGroup(List<T> src, IClassificationStrategy<T> strategy) {
        this.src = src;
        this.strategy = strategy;
        this.initMap();
    }

    /**
     * @param src       資料
     * @param groupSize 分組筆數
     */
    public SegmentGroup(List<T> src, int groupSize) {
        this(src, new SegmentGroupBase<>(src, groupSize));

    }


    private void initMap() {
        final Map<Integer, List<T>> groupMap = src.stream().collect(Collectors.groupingBy(this.strategy::toCategory));
        for (Map.Entry<Integer, List<T>> integerListEntry : groupMap.entrySet()) {
            this.map.put(integerListEntry.getKey(), new GroupTaskContext<>(integerListEntry.getValue()));
        }
        this.size = map.keySet().size();
    }


    @Override
    public boolean hasNext() {
        return index <= size - 1;
    }

    @Override
    public IGroupTaskContext<T> next() {
        if (hasNext()) {
            return map.get(index++);
        } else {
            return new GroupTaskContext<>(new ArrayList<>());
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
