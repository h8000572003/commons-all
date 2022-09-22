package io.github.h800572003.concurrent.group;

import java.util.List;

/**
 * 任務拆分服務
 */
public interface IGroupTaskService {

    /**
     * 任務執行
     * @param groupTaskOption
     * @param <T>
     */
    <T> void execute(GroupTaskOption<T> groupTaskOption);



    /**
     * 任務作法
     * @param <T>
     */
    interface IGroupTask<T> {
        /**
         * 執行任務
         * @param tasks
         */
        void run(List<T> tasks);
    }
}
