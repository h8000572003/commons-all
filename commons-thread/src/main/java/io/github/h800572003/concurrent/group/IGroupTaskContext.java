package io.github.h800572003.concurrent.group;

import java.util.List;

/**
 * 任務上下文
 * @param <T>
 */
public interface IGroupTaskContext<T> {

    /**
     * 上下文
     * @return
     */
    List<T> getTasks();

}
