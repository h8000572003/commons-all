package io.github.h800572003.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Slf4j
class ForkJoinTest {

    private final ForkJoinPool joinPool=new ForkJoinPool(3);

    @Test
    void test(){

        List<Integer> collect = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        for(Integer i:collect){
            joinPool.submit(new Wokr(i));
        }
        try {
            joinPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        joinPool.shutdown();

    }
    class Wokr extends RecursiveTask {

        private int sleep;

        public Wokr(int sleep) {
            this.sleep = sleep+1;
        }

        @Override
        protected Object compute() {
            try {
                log.info("sleep statt..{}", sleep);

                return "";
            }  finally{
                log.info("sleep end..{}",sleep);
            }

        }
    }
}
