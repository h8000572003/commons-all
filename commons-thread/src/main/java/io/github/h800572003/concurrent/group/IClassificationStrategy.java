package io.github.h800572003.concurrent.group;

/**
 * 分群策略
 * @param <T>
 */
public interface IClassificationStrategy<T> {
    /**
     *
     * @param data
     * @return
     */
    Integer toCategory(T data);
}
