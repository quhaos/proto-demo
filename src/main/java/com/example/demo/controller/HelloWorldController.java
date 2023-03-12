package com.example.demo.controller;

import com.al.cc.java.p2.FeedBackSchema;
import com.example.demo.entity.Feedback;
import com.example.demo.utils.CommonUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloWorldController {


    @RequestMapping(value="/feedbackJson" ,
            method=RequestMethod.POST ,
            consumes="application/json;charset=UTF-8")
    public String addFeedback(@RequestBody com.example.demo.vo.Feedback feedback){
        System.out.println(feedback);
        return feedback.toString();
    }



    public static void main(String[] args) throws InvalidProtocolBufferException {
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

        //{"lifeCycleId":1,"name":"test","gcId":"test","gender":"MALE","id":"test","parkingLotType":"UNDERGROUND","epay":true}
        String str = CommonUtils.toJsonStr(feedback);

        Long start1 = System.currentTimeMillis();
        for(int i=0;i<ic;i++){
            com.example.demo.vo.Feedback vo = CommonUtils.toPojo(str, com.example.demo.vo.Feedback.class);
        }
        System.out.printf("JSON 反序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - start1));

        Long start2 = System.currentTimeMillis();
        for(int i=0;i<ic;i++){
            String s1 = CommonUtils.toJsonStr(feedback);
        }
        System.out.printf("JSON 序列化%d次耗时%d：" ,ic, (System.currentTimeMillis() - start2));
        System.out.println("JSON 数据长度:"+ str.getBytes().length);

//        byte[] userJsonBytes = JSON.toJSONBytes(feedback);
//        System.out.println("fastJSON 数据长度:"+ userJsonBytes.length);

        //com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "createTime"
        String addStr = "{\"lifeCycleId\":1,\"name\":\"test\",\"gcId\":\"test\",\"gender\":\"MALE\",\"id\":\"test\",\"parkingLotType\":\"UNDERGROUND\",\"epay\":true,\"createTime\":1000}";
        com.example.demo.vo.Feedback vo = CommonUtils.toPojo(addStr, com.example.demo.vo.Feedback.class);
//        System.out.println(vo);
    }

    @RequestMapping(value="/feedbackPro2" ,
            method=RequestMethod.POST ,
            consumes="application/x-protobuf;charset=UTF-8")
    public ResponseEntity addFeedback(@RequestBody FeedBackSchema.Feedback feedback){
        System.out.println(feedback+ "\n length"+ feedback.getSerializedSize());

        Feedback feedbackEntity = new Feedback();
        feedbackEntity.setGcId(feedback.getGcId());
        feedbackEntity.setGender(feedback.getGender().getNumber());
        feedbackEntity.setLifeCycleId(feedback.getLifeCycleId());
        feedbackEntity.setName(feedback.getName());
        //parkinglot

        feedbackEntity.setEPay(feedback.getEPay());
        feedbackEntity.setParkingLotType(feedback.getParkingLotType().getNumber());
        feedbackEntity.setId(feedback.getId());

        System.out.println(feedbackEntity);
        // p3 default value :string ->'' number ->0  enum ->0   bool>false
        // p2 default value can custom defined


        // p3 client -> p3 server
        //all set value ----- Feedback(lifeCycleId=1, name=test, gcId=gcid, gender=0, id=parkingLotId1, parkingLotType=0, ePay=true)
        //part set value ----- Feedback(lifeCycleId=1, name=, gcId=, gender=0, id=, parkingLotType=0, ePay=false)


        // p2 client -> p3 server
        //all set value ----- Feedback(lifeCycleId=1, name=test, gcId=gcid, gender=0, id=parkingLotId1, parkingLotType=0, ePay=true)
        //part set value ----- Feedback(lifeCycleId=1, name=, gcId=, gender=0, id=, parkingLotType=0, ePay=false)


        //p3 client -> p2 server
        // all set value -----  Feedback(lifeCycleId=1, name=test, gcId=gcid, gender=1, id=parkingLotId1, parkingLotType=1, ePay=true)
        //part set value ----- Feedback(lifeCycleId=1, name=-9999, gcId=-9999, gender=1, id=-9999, parkingLotType=1, ePay=true)


        //p2 client -> p2 server
        // all set value -----  Feedback(lifeCycleId=1, name=test, gcId=gcid, gender=0, id=parkingLotId1, parkingLotType=0, ePay=true)
        //part set value -----Feedback(lifeCycleId=1, name=-9999, gcId=-9999, gender=1, id=-9999, parkingLotType=1, ePay=true)

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value="/feedbackPro3" ,
            method=RequestMethod.POST ,
            consumes="application/x-protobuf;charset=UTF-8")
    public ResponseEntity addFeedback(@RequestBody com.al.cc.java.p3.FeedBackSchema.Feedback feedback){
        System.out.println(feedback+ "\n length"+ feedback.getSerializedSize());

        Feedback feedbackEntity = new Feedback();
        feedbackEntity.setGcId(feedback.getGcId());
        feedbackEntity.setGender(feedback.getGender().getNumber());
        feedbackEntity.setLifeCycleId(feedback.getLifeCycleId());
        feedbackEntity.setName(feedback.getName());
        //parkinglot

        feedbackEntity.setEPay(feedback.getEPay());
        feedbackEntity.setParkingLotType(feedback.getParkingLotType().getNumber());
        feedbackEntity.setId(feedback.getId());

        System.out.println(feedbackEntity);
        return new ResponseEntity(HttpStatus.OK);
    }


}
