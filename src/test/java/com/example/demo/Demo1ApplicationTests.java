package com.example.demo;

import com.al.cc.java.p3.FeedBackSchema;
import com.alibaba.fastjson.JSON;
import com.example.demo.utils.CommonUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() throws InvalidProtocolBufferException {

            com.al.cc.java.p3.FeedBackSchema.Feedback feedBackSchema3  = com.al.cc.java.p3.FeedBackSchema.Feedback.newBuilder()
                    .setEPay(true)
                    .setGcId("test")
                    .setName("test")
                    .setGender(com.al.cc.java.p3.FeedBackSchema.Gender.MALE)
                    .setLifeCycleId(1)
                    .setId("test")
                    .setParkingLotType(com.al.cc.java.p3.FeedBackSchema.ParkingLotType.UNDERGROUND)
                    .build();
            com.al.cc.java.p2.FeedBackSchema.Feedback feedBackSchema2  = com.al.cc.java.p2.FeedBackSchema.Feedback.newBuilder()
                    .setEPay(true)
                    .setGcId("test")
                    .setName("test")
                    .setGender(com.al.cc.java.p2.FeedBackSchema.Gender.MALE)
                    .setLifeCycleId(1)
                    .setId("test")
                    .setParkingLotType(com.al.cc.java.p2.FeedBackSchema.ParkingLotType.UNDERGROUND)
                    .build();
            //ProtoBuf2 反序列化1000次耗时47：ProtoBuf2 序列化1000次耗时0：PROTO2 数据长度26
            //ProtoBuf2 反序列化1000次耗时15：ProtoBuf3 序列化1000次耗时0：PROTO3 数据长度24
            //JSON 反序列化1000次耗时47：JSON 序列化1000次耗时0：JSON 数据长度:579
            //ProtoBuf2 反序列化10000次耗时32：PROTO2 数据长度26
            //ProtoBuf3 反序列化10000次耗时16：PROTO3 数据长度24
            //JSON 反序列化10000次耗时313：JSON 数据长度:579
            //ProtoBuf2 反序列化100000次耗时62：PROTO2 数据长度26
            //ProtoBuf3 反序列化100000次耗时47：PROTO3 数据长度24
            //JSON 反序列化100000次耗时1236：JSON 数据长度:579
            //ProtoBuf2 反序列化1000000次耗时188：PROTO2 数据长度26
            //ProtoBuf3 反序列化1000000次耗时125：PROTO3 数据长度24
            //JSON 反序列化1000000次耗时9634：JSON 数据长度:579
            int ic = 1000000;
            Long s = System.currentTimeMillis();
            for(int i=0;i<ic;i++){
                com.al.cc.java.p2.FeedBackSchema.Feedback f = com.al.cc.java.p2.FeedBackSchema.Feedback.parseFrom(feedBackSchema2.toByteArray());
            }
            System.out.printf("ProtoBuf2 反序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - s));

            Long encodeStart = System.currentTimeMillis();

            for(int i=0;i<ic;i++){
                feedBackSchema2.toByteArray();
            }
            System.out.printf("ProtoBuf2 序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - encodeStart));
            System.out.println("PROTO2 数据长度"+ feedBackSchema2.toByteArray().length);
            Long start = System.currentTimeMillis();
            for(int i=0;i<ic;i++){
                com.al.cc.java.p3.FeedBackSchema.Feedback f = com.al.cc.java.p3.FeedBackSchema.Feedback.parseFrom(feedBackSchema3.toByteArray());
            }
            System.out.printf("ProtoBuf3 反序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - start));
            Long encodeStart1 = System.currentTimeMillis();
            for(int i=0;i<ic;i++){
                feedBackSchema2.toByteArray();
            }
            System.out.printf("ProtoBuf3 序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - encodeStart1));
            System.out.println("PROTO3 数据长度"+ feedBackSchema3.toByteArray().length);
            com.example.demo.vo.Feedback feedback =new com.example.demo.vo.Feedback();
            feedback.setEPay(true);
            feedback.setGcId("test");
            feedback.setName("test");
            feedback.setGender(com.example.demo.vo.Feedback.Gender.MALE);
            feedback.setLifeCycleId(1l);
            feedback.setId("test");
            feedback.setParkingLotType(com.example.demo.vo.Feedback.ParkingLotType.UNDERGROUND);
            String str = CommonUtils.toJsonStr(feedback);
            Long start1 = System.currentTimeMillis();
            for(int i=0;i<ic;i++){
                com.example.demo.vo.Feedback vo = CommonUtils.toPojo(str, com.example.demo.vo.Feedback.class);
            }
            System.out.printf("JACKSON 反序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - start1));

            Long start2 = System.currentTimeMillis();
            for(int i=0;i<ic;i++){
                CommonUtils.toJsonStr(feedback);
            }
            System.out.printf("JACKSON 序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - start2));
            System.out.println("JACKSON 数据长度:"+ str.getBytes().length);

            byte[] userJsonBytes = JSON.toJSONBytes(feedback);
            System.out.println("FASTJSON 数据长度:"+ userJsonBytes.length);
    }



    @Test
    public void test2(){

            com.al.cc.java.p3.FeedBackSchema.Feedback feedBackSchema1  = com.al.cc.java.p3.FeedBackSchema.Feedback.newBuilder()
                    .setGcId("test")
                    .setName("test")
                    .setGender(FeedBackSchema.Gender.FEMALE) //default 0 index enum
                    .setLifeCycleId(1)
                    .setId("test")
                    .setParkingLotType(com.al.cc.java.p3.FeedBackSchema.ParkingLotType.UNDERGROUND) //default 0 index enum
                    .build();



            com.al.cc.java.p3.FeedBackSchema.Feedback feedBackSchema2  = com.al.cc.java.p3.FeedBackSchema.Feedback.newBuilder()
                    .setEPay(true)
                    .setGcId("test")
                    .setName("test")
                    .setGender(FeedBackSchema.Gender.MALE)
                    .setLifeCycleId(1)
                    .setId("test")
                    .setParkingLotType(FeedBackSchema.ParkingLotType.OUTSIDE)
                    .build();

            System.out.println(feedBackSchema1.toByteArray().length);
            System.out.println(feedBackSchema2.toByteArray().length);

    }

}
