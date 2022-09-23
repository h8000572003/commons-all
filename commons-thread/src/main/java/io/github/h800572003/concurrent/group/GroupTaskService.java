package io.github.h800572003.concurrent.group;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class GroupTaskService implements IGroupTaskService {


    @Override
    public <T> void execute(GroupTaskOption<T> groupTaskOption) {
        new Service(groupTaskOption).run();
    }

    static class Service<T> {

        GroupTaskOption<T> groupTaskOption;

        final ScheduledExecutorService scheduledExecutorService;
        final CountDownLatch countDownLatch;


        public Service(GroupTaskOption<T> groupTaskOption) {
            this.groupTaskOption = groupTaskOption;

            final int threadSize = groupTaskOption.getThreadSize();
            final String name = groupTaskOption.getName();
            IGroup<T> group = groupTaskOption.getGroup();
            log.debug("thread:{} group size:{},date size:{}", threadSize, group.getSize(), groupTaskOption.getTasks().size());
            this.scheduledExecutorService = Executors.newScheduledThreadPool(threadSize, new CustomizableThreadFactory(name));
            this.countDownLatch = new CountDownLatch(group.getSize());
        }

        public void run() {
            IGroup<T> group = groupTaskOption.getGroup();
            final List<Holder> holders = new ArrayList<>();
            try {
                while (group.hasNext()) {
                    final IGroupTaskContext<T> next = group.next();
                    final List<T> tasks = next.getTasks();
                    Holder holder = new Holder(this);
                    holder.run(tasks);
                    holders.add(holder);

                }
                countDownLatch.await();
            } catch (InterruptedException e) {
                scheduledExecutorService.shutdown();
                holders.forEach(Holder::close);
            } finally {
                scheduledExecutorService.shutdownNow();
            }
        }


    }

    static class Holder<T> {

        final Service<T> service;


        private Future<?> submit;


        public Holder(Service<T> service) {
            this.service = service;


        }

        public void run(List<T> tasks) {
            this.submit = this.service.scheduledExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        service.groupTaskOption.getTask().run(tasks);
                    } finally {
                        service.countDownLatch.countDown();
                    }
                }
            });
        }

        public void close() {
            if (!submit.isDone()) {
                try {
                    submit.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
