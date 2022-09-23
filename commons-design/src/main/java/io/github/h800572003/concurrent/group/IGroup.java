package io.github.h800572003.concurrent.group;

import java.util.Iterator;

/**
 * 分群
 * @param <T>
 */
public interface IGroup<T> extends Iterator<IGroupTaskContext<T>> {


    int getSize();

}
