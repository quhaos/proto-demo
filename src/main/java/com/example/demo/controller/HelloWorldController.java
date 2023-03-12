package com.example.demo.controller;

import com.al.cc.java.p2.FeedBackSchema;
import com.example.demo.entity.Feedback;
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
    @RequestMapping(value="/feedbackPro2" ,
            method=RequestMethod.POST ,
            consumes="application/x-protobuf;charset=UTF-8")
    public ResponseEntity addFeedback(@RequestBody FeedBackSchema.Feedback feedback){
        System.out.println("============= p2 interface ==================");
        System.out.println(feedback);
        Feedback feedbackEntity = new Feedback();
        feedbackEntity.setGcId(feedback.getGcId());
        feedbackEntity.setGender(feedback.getGender().getNumber());
        feedbackEntity.setLifeCycleId(feedback.getLifeCycleId());
        feedbackEntity.setName(feedback.getName());
        feedbackEntity.setEPay(feedback.getEPay()?1:0);
        feedbackEntity.setParkingLotType(feedback.getParkingLotType().getNumber());
        feedbackEntity.setId(feedback.getId());
        System.out.println(feedbackEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value="/feedbackPro3" ,
            method=RequestMethod.POST ,
            consumes="application/x-protobuf;charset=UTF-8")
    public ResponseEntity addFeedback(@RequestBody com.al.cc.java.p3.FeedBackSchema.Feedback feedback){
        System.out.println("============= p3 interface ==================");
        System.out.println(feedback);
        Feedback feedbackEntity = new Feedback();
        feedbackEntity.setGcId(feedback.getGcId());
        feedbackEntity.setGender(feedback.getGender().getNumber());
        feedbackEntity.setLifeCycleId(feedback.getLifeCycleId());
        feedbackEntity.setName(feedback.getName());
        feedbackEntity.setEPay(feedback.getEPay()?1:0);
        feedbackEntity.setParkingLotType(feedback.getParkingLotType().getNumber());
        feedbackEntity.setId(feedback.getId());
        System.out.println(feedbackEntity);
        return new ResponseEntity(HttpStatus.OK);
    }


}
