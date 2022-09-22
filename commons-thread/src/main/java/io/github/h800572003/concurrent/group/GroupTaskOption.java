package io.github.h800572003.concurrent.group;

import io.github.h800572003.exception.ApBusinessException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分組參數
 * @param <T>
 */
@Data
public class GroupTaskOption<T> {
    private final String name;
    private final int groupSize;
    private final int threadSize;
    private final List<T> tasks;
    private final IGroupTaskService.IGroupTask<T> task;

    private final IGroup<T> group;



    GroupTaskOption(GroupTaskOptionBuilder<T> builder) {
        this.name = builder.name;
        this.groupSize = builder.groupSize;
        this.threadSize = builder.threadSize;
        this.tasks = builder.tasks;
        this.task = builder.task;
        this.group = builder.group;
    }

    public static class GroupTaskOptionBuilder<T> {
        private String name;
        private int groupSize = 0;
        private int threadSize = 0;
        private List<T> tasks = new ArrayList<>();

        private IGroup<T> group;
        private IGroupTaskService.IGroupTask<T> task;



        public GroupTaskOptionBuilder<T>  name(String name){
            this.name=name;
            return this;
        }
        public GroupTaskOptionBuilder<T>  groupSize(int groupSize){
            this.groupSize=groupSize;
            return this;
        }
        public GroupTaskOptionBuilder<T>  threadSize(int threadSize){
            this.threadSize=threadSize;
            return this;
        }
        public GroupTaskOptionBuilder<T>  tasks(List<T> tasks){
            this.tasks=tasks;
            return this;
        }
        public GroupTaskOptionBuilder<T>  group(IGroup<T> group){
            this.group=group;
            return this;
        }
        public GroupTaskOptionBuilder<T>  task(IGroupTaskService.IGroupTask<T> task){
            this.task=task;
            return this;
        }

        public GroupTaskOption<T> build() {
            if (StringUtils.isBlank(name)) {
                throw new ApBusinessException("請輸入名稱");
            } else if (threadSize == 0) {
                throw new ApBusinessException("工人數不得同時為0");
            } else if (tasks == null) {
                throw new ApBusinessException("任務不得null");
            } else if (task == null) {
                throw new ApBusinessException("無提供執行方案");
            }

            if (this.groupSize == 0) {
                this.groupSize = threadSize;
            }
            if (this.group == null) {
                this.group = new SegmentGroup<>(tasks, groupSize);
            }


            return new GroupTaskOption<>(this);
        }
    }


}
