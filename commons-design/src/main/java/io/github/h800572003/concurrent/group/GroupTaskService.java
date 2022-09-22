package io.github.h800572003.concurrent.group;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class GroupTaskService implements IGroupTaskService {


    @Override
    public <T> void execute(GroupTaskOption<T> groupTaskOption) {

        final int threadSize = groupTaskOption.getThreadSize();
        final String name = groupTaskOption.getName();
        IGroup<T> group = groupTaskOption.getGroup();
        log.debug("thread:{} group size:{},date size:{}", threadSize, group.getSize(), groupTaskOption.getTasks().size());
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(threadSize, new CustomizableThreadFactory(name));
        try {

            CountDownLatch countDownLatch = new CountDownLatch(group.getSize());
            while (group.hasNext()) {
                IGroupTaskContext<T> next = group.next();

                List<T> tasks = next.getTasks();
                scheduledExecutorService.execute(() -> {
                    try {
                        groupTaskOption.getTask().run(tasks);
                    } finally {
                        countDownLatch.countDown();
                    }
                });

            }
            countDownLatch.await();
            log.debug("down ok");
        } catch (InterruptedException e) {
            scheduledExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
        } finally {
            scheduledExecutorService.shutdownNow();
        }
    }


}
