package io.github.h800572003.concurrent.group;

import java.util.List;

public class GroupTaskContext<T> implements IGroupTaskContext<T> {

    private final List<T> tasks;




    public GroupTaskContext(List<T> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<T> getTasks() {
        return tasks;
    }

}
