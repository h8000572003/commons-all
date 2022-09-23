package io.github.h800572003.concurrent.group;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SegmentKeyGroupBaseTest {


    private SegmentKeyGroupBase groupBase;


    @Test
    void testToCategory() {


        //GIVE
        SegmentData segmentData1 = new SegmentData("A", "A1");
        SegmentData segmentData2 = new SegmentData("A", "A1");
        SegmentData segmentData3 = new SegmentData("A", "A2");
        SegmentData segmentData4 = new SegmentData("A", "A3");

        SegmentData segmentData5 = new SegmentData("B", "B1");


        SegmentData segmentData6 = new SegmentData("C", "C1");
        SegmentData segmentData7 = new SegmentData("C", "C2");


        List<SegmentData> list = Lists.list(//
                segmentData1,//
                segmentData3,//
                segmentData4,//
                segmentData5,//
                segmentData6,//
                segmentData2,//
                segmentData7//
        );

        //WHEN
        SegmentKeyGroupBase segmentKeyGroupBase = new SegmentKeyGroupBase(
                (SegmentKeyGroupBase.SegmentKeyFilter<SegmentData>) SegmentData::getKey,
                2);

        //THEN
        Integer integer1 = segmentKeyGroupBase.toCategory(segmentData1);
        Integer integer2 = segmentKeyGroupBase.toCategory(segmentData2);
        Integer integer3 = segmentKeyGroupBase.toCategory(segmentData3);
        Integer integer4 = segmentKeyGroupBase.toCategory(segmentData4);


        assertThat(integer1).isNotNull();
        assertThat(integer2).isNotNull();
        assertThat(integer3).isNotNull();
        assertThat(integer4).isNotNull();


        assertThat(integer1).isEqualTo(integer2);
        assertThat(integer1).isEqualTo(integer3);
        assertThat(integer1).isEqualTo(integer4);


        Integer integer6 = segmentKeyGroupBase.toCategory(segmentData6);
        Integer integer7 = segmentKeyGroupBase.toCategory(segmentData7);

        assertThat(integer6).isNotNull();
        assertThat(integer7).isNotNull();

        assertThat(integer6).isEqualTo(integer7);


    }


    class SegmentData {
        private String body;
        private String key;

        public SegmentData(String key, String body) {
            this.body = body;
            this.key = key;
        }

        public SegmentData() {

        }

        public String getBody() {
            return body;
        }

        public String getKey() {
            return key;
        }
    }


}