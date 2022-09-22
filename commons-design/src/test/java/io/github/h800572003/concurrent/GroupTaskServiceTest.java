package io.github.h800572003.concurrent;

import io.github.h800572003.concurrent.group.GroupTaskOption;
import io.github.h800572003.concurrent.group.GroupTaskService;
import io.github.h800572003.concurrent.group.IGroupTaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class GroupTaskServiceTest {




    @BeforeEach
    void init() {


    }

    /**
     * 給 0~10任務
     * 分3組人執行
     * 總共執行3次任務
     */
    @Test
    void execute() {
        exe(3, 2, 10,0);
    }
    @Test
    void execute100() {
        exe(3, 2, 1000,0);
    }

    @Test
    @Timeout(3)
    void executeInterrupt() throws InterruptedException {

        Thread thread = new Thread(() -> exe(10, 2, 300,300));
        thread.start();



        TimeUnit.MILLISECONDS.sleep(1000);

        thread.interrupt();

        thread.join();


        log.info("ok");
    }



    private  void exe(int groupSize, int threadSize, int dataSize,int workSize) {
         GroupTaskService groupTaskService = new GroupTaskService();
        //init task
        final IGroupTaskService.IGroupTask<Integer> run = Mockito.spy(new IGroupTaskService.IGroupTask<Integer>() {

            @Override
            public void run(List<Integer> tasks) {
                log.info("start size:{} :{}" ,tasks.size(),tasks);
                if(workSize>0){
                    try {
                        TimeUnit.MILLISECONDS.sleep(workSize);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("end");
            }
        });

        //GIVE
        final List<Integer> collect = IntStream.range(0, dataSize)//
                .boxed()//
                .collect(Collectors.toList());//


        GroupTaskOption.GroupTaskOptionBuilder<Integer> groupTaskOptionBuilder = new GroupTaskOption.GroupTaskOptionBuilder();
        GroupTaskOption<Integer> input = groupTaskOptionBuilder
                .name("test")//
                .threadSize(threadSize)//
                .groupSize(groupSize)//
                .task(run)//
                .tasks(collect)//
                .build();

        //when
        groupTaskService.execute(input);

        //then
        Mockito.verify(run, Mockito.times(groupSize)).run(Mockito.anyList());
    }


}