package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.utils.CommonUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;


/**
 * p2 24b -> 反序列化后对象在堆中实例大小为64 . 对象转化为DTO 对象为48. 约等于5倍大小
 * 200KB转化为JAVA对象占用1M. 1000个用户占用1G. 抛去线程开销，各种实例创建，1000个用户 大约要用2G. 60% 4g内存。 2个CPU合适
 */
public  class Demo1ApplicationTests {
    private  int ic = 1000000;
    @Test
    public void testP3() throws InvalidProtocolBufferException {

        com.al.cc.java.p3.FeedBackSchema.Feedback feedBackSchema3 = com.al.cc.java.p3.FeedBackSchema.Feedback.newBuilder()
                .setEPay(true)
                .setGcId("test")
                .setName("test")
                .setGender(com.al.cc.java.p3.FeedBackSchema.Gender.MALE)
                .setLifeCycleId(1)
                .setId("test")
                .setParkingLotType(com.al.cc.java.p3.FeedBackSchema.ParkingLotType.UNDERGROUND)
                .build();
        System.out.printf("ProtoBuf3 数据长度:%d ,",feedBackSchema3.getSerializedSize());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("t1");
        for (int i = 0; i < ic; i++) {
            com.al.cc.java.p3.FeedBackSchema.Feedback.parseFrom(feedBackSchema3.toByteArray());
        }
        stopWatch.stop();
        System.out.printf("反序列化%d次耗时:%d ,", ic, stopWatch.getTotalTimeMillis());

        stopWatch.start("t2");
        for (int i = 0; i < ic; i++) {
            feedBackSchema3.toByteArray();
        }
        stopWatch.stop();
        System.out.printf("序列化%d次耗时:%d \n", ic, stopWatch.getTotalTimeMillis());
    }

    @Test
    public void testP2() throws InvalidProtocolBufferException {

        com.al.cc.java.p2.FeedBackSchema.Feedback feedBackSchema2 = com.al.cc.java.p2.FeedBackSchema.Feedback.newBuilder()
                .setEPay(true)
                .setGcId("test")
                .setName("test")
                .setGender(com.al.cc.java.p2.FeedBackSchema.Gender.MALE)
                .setLifeCycleId(1)
                .setId("test")
                .setParkingLotType(com.al.cc.java.p2.FeedBackSchema.ParkingLotType.UNDERGROUND)
                .build();
        System.out.printf("ProtoBuf2 数据长度:%d ," ,feedBackSchema2.getSerializedSize());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("t1");
        for (int i = 0; i < ic; i++) {
            com.al.cc.java.p2.FeedBackSchema.Feedback.parseFrom(feedBackSchema2.toByteArray());
        }
        stopWatch.stop();
        System.out.printf("反序列化%d次耗时:%d ,", ic, stopWatch.getTotalTimeMillis());

        stopWatch.start("t2");
        for (int i = 0; i < ic; i++) {
            feedBackSchema2.toByteArray();
        }
        stopWatch.stop();
        System.out.printf("序列化%d次耗时:%d \n ", ic, stopWatch.getTotalTimeMillis());
    }
    @Test
    public  void testFastJson(){
        com.example.demo.vo.Feedback feedback = new com.example.demo.vo.Feedback();
        feedback.setEPay(true);
        feedback.setGcId("test");
        feedback.setName("test");
        feedback.setGender(com.example.demo.vo.Feedback.Gender.MALE);
        feedback.setLifeCycleId(1l);
        feedback.setId("test");
        feedback.setParkingLotType(com.example.demo.vo.Feedback.ParkingLotType.UNDERGROUND);
        String str = JSON.toJSONString(feedback);
        int ic = 1000000;

        byte[] userJsonBytes = JSON.toJSONBytes(feedback);
        System.out.printf("FASTJSON 数据长度:%d ," ,userJsonBytes.length);

        StopWatch stopWatch =new StopWatch();
        stopWatch.start("t1");
        for (int i = 0; i < ic; i++) {
           JSON.parseObject(str,com.example.demo.vo.Feedback.class);
        }
        stopWatch.stop();
        System.out.printf("反序列化%d次耗时:%d ,", ic, stopWatch.getTotalTimeMillis());

        stopWatch.start("t2");
        for (int i = 0; i < ic; i++) {
            JSON.toJSONString(feedback);
        }
        stopWatch.stop();
        System.out.printf("序列化%d次耗时:%d \n", ic, stopWatch.getTotalTimeMillis());


    }
    @Test
    public void testJackson(){
        com.example.demo.vo.Feedback feedback = new com.example.demo.vo.Feedback();
        feedback.setEPay(true);
        feedback.setGcId("test");
        feedback.setName("test");
        feedback.setGender(com.example.demo.vo.Feedback.Gender.MALE);
        feedback.setLifeCycleId(1l);
        feedback.setId("test");
        feedback.setParkingLotType(com.example.demo.vo.Feedback.ParkingLotType.UNDERGROUND);
        int ic = 1000000;

        String str = CommonUtils.toJsonStr(feedback);
        System.out.printf("JACKSON 数据长度:%d ,",str.getBytes().length);
        StopWatch stopWatch =new StopWatch();
        stopWatch.start("t1");
        for (int i = 0; i < ic; i++) {
            CommonUtils.toPojo(str, com.example.demo.vo.Feedback.class);
        }
        stopWatch.stop();
        System.out.printf("反序列化%d次耗时:%d ,", ic, stopWatch.getTotalTimeMillis());

        stopWatch.start("t2");
        for (int i = 0; i < ic; i++) {
            CommonUtils.toJsonStr(feedback);
        }
        stopWatch.stop();
        System.out.printf("序列化%d次耗时:%d \n", ic, stopWatch.getTotalTimeMillis());
    }


}
