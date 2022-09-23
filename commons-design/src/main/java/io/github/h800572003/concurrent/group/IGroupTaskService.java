package io.github.h800572003.concurrent.group;

import java.util.List;

public interface IGroupTaskService {

    /**
     */
    <T> void execute(GroupTaskOption<T> groupTaskOption);



    interface IGroupTask<T> {
        void run(List<T> tasks);
    }
}
