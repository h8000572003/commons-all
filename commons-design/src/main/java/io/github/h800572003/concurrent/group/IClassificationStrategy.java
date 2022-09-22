package io.github.h800572003.concurrent.group;

public interface IClassificationStrategy<T> {
    Integer toCategory(T data);
}
