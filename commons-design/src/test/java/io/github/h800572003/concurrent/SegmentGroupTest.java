package io.github.h800572003.concurrent;

import io.github.h800572003.concurrent.group.IGroupTaskContext;
import io.github.h800572003.concurrent.group.SegmentGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SegmentGroupTest {

    @Test
    void next3() {

        //GIVE 3組
        test(3,3);


    }
    @Test
    void next20() {

        //GIVE 20組

        //THEN 9組
        test(20,9);


    }

    /**
     * 10筆資料
     * @param groupSize 分組數量
     * @param assertSize 最終分組數量
     */
    private static void test(int groupSize,int assertSize) {
        //GIVE 1~9
        List<Integer> list = IntStream.range(1, 10).boxed().collect(Collectors.toList());

        //WHEN
        SegmentGroup<Integer> segmentGroup = new SegmentGroup(list, groupSize);


        //THEN
        int loopTime = 0;
        int sum = 0;
        while (segmentGroup.hasNext()) {
            loopTime++;
            IGroupTaskContext<Integer> next = segmentGroup.next();
            for (Integer i : next.getTasks() ) {
                sum+=i;
            }
            log.info("next:{}", next.getTasks());
        }
        assertThat(loopTime).isEqualTo(assertSize);
        assertThat(sum).isEqualTo(45);
    }

}